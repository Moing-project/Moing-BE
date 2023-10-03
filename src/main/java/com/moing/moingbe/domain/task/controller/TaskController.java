package com.moing.moingbe.domain.task.controller;

import com.moing.moingbe.domain.task.dto.TaskCreateRequestDto;
import com.moing.moingbe.domain.task.service.TaskService;
import com.moing.moingbe.global.dto.MessageResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDto> createTask(@RequestBody TaskCreateRequestDto requestDto){
        return ResponseEntity.ok(taskService.createTask(requestDto));
    }

    public ResponseEntity<MessageResponseDto> updateTask(){
        return null;
    }

    public ResponseEntity<MessageResponseDto> deleteTask(){
        return null;
    }
}
