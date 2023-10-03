package com.moing.moingbe.domain.task.service;

import com.moing.moingbe.domain.task.dto.TaskCreateRequestDto;
import com.moing.moingbe.domain.task.entity.Task;
import com.moing.moingbe.domain.task.entity.TaskCollaborators;
import com.moing.moingbe.domain.task.repository.TaskCollaboratorsRepository;
import com.moing.moingbe.domain.task.repository.TaskRepository;
import com.moing.moingbe.global.dto.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.moing.moingbe.global.enums.AccessCode.TASK_CREATED;

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

        Task task = taskRepository.save(new Task(requestDto));
        for (Long userId : requestDto.getUserIds()) {
            taskCollaboratorsRepository.save(new TaskCollaborators(task.getId(), userId));
        }
        return MessageResponseDto.out(TASK_CREATED);
    }
}
