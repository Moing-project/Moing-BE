package com.moing.moingbe.domain.task.service;

import com.moing.moingbe.domain.auth.dto.AuthWorkSoloResponseDto;
import com.moing.moingbe.domain.task.dto.TaskCreateRequestDto;
import com.moing.moingbe.domain.task.entity.Task;
import com.moing.moingbe.domain.task.entity.TaskCollaborators;
import com.moing.moingbe.domain.task.repository.TaskCollaboratorsRepository;
import com.moing.moingbe.domain.task.repository.TaskRepository;
import com.moing.moingbe.global.dto.BaseResponseDto;
import com.moing.moingbe.global.dto.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.moing.moingbe.global.enums.AccessCode.*;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskCollaboratorsRepository taskCollaboratorsRepository;

    public TaskService(TaskRepository taskRepository, TaskCollaboratorsRepository taskCollaboratorsRepository) {
        this.taskRepository = taskRepository;
        this.taskCollaboratorsRepository = taskCollaboratorsRepository;
    }

    public MessageResponseDto createTask(TaskCreateRequestDto requestDto) {

        Long taskId = taskRepository.finalTask(requestDto.getWorkId());

        if (taskId == null)
            taskId = 0L;

        Task task = taskRepository.save(new Task(taskId + 1L, requestDto));
        for (Long userId : requestDto.getUserIds()) {
            taskCollaboratorsRepository.save(new TaskCollaborators(task.getId(), userId));
        }
        return MessageResponseDto.out(TASK_CREATED);
    }

    public BaseResponseDto<Page<Task>> getWorkTask(Long workId, Pageable pageable) {
        return BaseResponseDto.builder(taskRepository.taskList(workId, pageable)).code(TASK_LOADING).build();
    }

    public BaseResponseDto<List<AuthWorkSoloResponseDto>> getWorkUsers(Long workId) {
        return null;
    }

    public MessageResponseDto getWorkTaskOne(Long workId, Long taskId) {
        return null;
    }

}
