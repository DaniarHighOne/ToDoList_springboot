package com.example.taskList.web.dto.task;

import com.example.taskList.domain.task.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Status status;//enum for status
    private LocalDateTime expirationDate;//deadline for task
}
