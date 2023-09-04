package com.moing.moingbe.domain.team.controller;

import com.moing.moingbe.domain.team.dto.TeamMainResponseDto;
import com.moing.moingbe.domain.team.dto.TeamOneResponseDto;
import com.moing.moingbe.domain.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public ResponseEntity<List<TeamMainResponseDto>> getTeamMain(

    ){
        return ResponseEntity.ok(teamService.getTeamMain());
    }


    @GetMapping("/{workId}")
    public ResponseEntity<TeamOneResponseDto> getTeamOne(@PathVariable Long workId){
        return ResponseEntity.ok(teamService.getTeamOne(workId));
    }
}
