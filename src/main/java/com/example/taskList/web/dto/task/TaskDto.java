package com.example.taskList.web.dto.task;

import com.example.taskList.domain.task.Status;
import com.example.taskList.web.dto.validation.OnCreate;
import com.example.taskList.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    @NotNull(message="Couldn't be null here", groups = OnUpdate.class)
    private Long id;

    @NotNull(message="Title must not be null", groups = {OnCreate.class,OnUpdate.class})
    @Length(max=255, message="The length should be less 255 symbols",groups = {OnCreate.class,OnUpdate.class})
    private String title;

    @Length(max=255, message="The length should be less 255 symbols",groups = {OnCreate.class,OnUpdate.class})
    private String description;

    private Status status;//enum for status

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)//format for date from annotation
    @JsonFormat(pattern= "yyyy-MM-dd HH:mm")//time pattern
    private LocalDateTime expirationDate;//deadline for task
}
