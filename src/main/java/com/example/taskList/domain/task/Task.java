package com.example.taskList.domain.task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;//enum for status
    private LocalDateTime expirationDate;//deadline for task
}
