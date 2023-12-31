package com.moing.moingbe.global.sercurity;


import com.moing.moingbe.domain.auth.entity.AuthUser;
import com.moing.moingbe.domain.auth.repository.AuthRepository;
import com.moing.moingbe.global.jwt.JwtUtil;
import com.moing.moingbe.global.redis.service.AuthenticationRedisService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.moing.moingbe.global.redis.service.AuthenticationRedisService.AuthenticationTarget;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository userRepository;

    private final AuthenticationRedisService authenticationRedisService;

    private final JwtUtil jwtUtil;

    public UserDetailsServiceImpl(AuthRepository userRepository, AuthenticationRedisService authenticationRedisService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationRedisService = authenticationRedisService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + email));

        return new UserDetailsImpl(user);
    }

    public void refreshToken(String email, HttpServletResponse response) {

        String value = UUID.randomUUID().toString();
        while (authenticationRedisService.isValue(value,AuthenticationTarget.TOKEN)){
            value = UUID.randomUUID().toString();
        }
        String token = authenticationRedisService.getValue(email, AuthenticationTarget.ID);
        if(token != null){
            authenticationRedisService.deleteKey(email, AuthenticationTarget.ID);
            authenticationRedisService.deleteKey(token, AuthenticationTarget.TOKEN);
        }
        redisAddToken(value, email);
        token = jwtUtil.createToken(value);
        jwtUtil.addJwtToHeader(token, response, JwtUtil.REFRESH_TOKEN_HEADER);
    }

    private void redisAddToken(String value, String username) {
        authenticationRedisService.setValueByExpireHour(username, value, 24L, AuthenticationTarget.ID);
        authenticationRedisService.setValueByExpireHour(value, username, 24L, AuthenticationTarget.TOKEN);
    }
}