package com.moing.moingbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoingBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoingBeApplication.class, args);
    }

}
