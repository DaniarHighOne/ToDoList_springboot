package com.example.taskList.service.impl;

import com.example.taskList.domain.exception.ResourceNotFoundException;
import com.example.taskList.domain.task.Status;
import com.example.taskList.domain.task.Task;
import com.example.taskList.repository.TaskRepository;
import com.example.taskList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)//where we only get object we add read only
    public List<Task> getAllByUserId(Long id) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    @Transactional//when object is about to change we do not add read only
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TO_DO);
        }
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {//new task is always todo
        task.setStatus(Status.TO_DO);
        taskRepository.create(task);
        taskRepository.assignByUserID(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }
}
