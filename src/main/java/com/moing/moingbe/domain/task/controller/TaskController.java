package com.moing.moingbe.domain.task.controller;

import com.moing.moingbe.domain.auth.dto.AuthWorkSoloResponseDto;
import com.moing.moingbe.domain.task.dto.TaskCreateRequestDto;
import com.moing.moingbe.domain.task.entity.Task;
import com.moing.moingbe.domain.task.service.TaskService;
import com.moing.moingbe.global.dto.BaseResponseDto;
import com.moing.moingbe.global.dto.MessageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{workId}")
    public ResponseEntity<BaseResponseDto<Page<Task>>> getWorkTask(@PathVariable Long workId, Pageable pageable){
        return ResponseEntity.ok(taskService.getWorkTask(workId,pageable));
    }
    @GetMapping("/{workId}/users")
    public ResponseEntity<BaseResponseDto<List<AuthWorkSoloResponseDto>>> getWorkUsers(@PathVariable Long workId){
        return ResponseEntity.ok(taskService.getWorkUsers(workId));
    }

    @GetMapping("/{workId}/{taskId}")
    public ResponseEntity<MessageResponseDto> getWorkTaskOne(@PathVariable Long workId, @PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getWorkTaskOne(workId,taskId));
    }

    public ResponseEntity<MessageResponseDto> updateTask(){
        return null;
    }

    public ResponseEntity<MessageResponseDto> deleteTask(){
        return null;
    }
}
