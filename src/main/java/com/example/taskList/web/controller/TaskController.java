package com.example.taskList.web.controller;

import com.example.taskList.domain.task.Task;
import com.example.taskList.service.TaskService;
import com.example.taskList.web.dto.task.TaskDto;
import com.example.taskList.web.dto.validation.OnUpdate;
import com.example.taskList.web.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController//spring util for returning json
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor//to get rid off Autowired
@Validated
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    /**
     * @Validated will validate all Dto with oOnUpdate.class mentions
     * here will be only 3 endpoints
     */
    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);

    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.getById(id);
    }
}
