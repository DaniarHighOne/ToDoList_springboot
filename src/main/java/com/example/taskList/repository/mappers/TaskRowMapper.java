package com.example.taskList.repository.mappers;

import com.example.taskList.domain.task.Status;
import com.example.taskList.domain.task.Task;

import java.sql.ResultSet;

public class TaskRowMapper {

    //get the set of sql request and return an object
    public static Task mapRow(ResultSet resultSet) {
        if(resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            task.setTitle(resultSet.getString("task_title"));
            task.setDescription(resultSet.getString("task_description"));
            task.setStatus(Status.valueOf(resultSet.getString("task_status")));
            task.setExpirationDate(resultSet.getTimestamp("task_expiration_date").toLocalDateTime());
            return task;
        }
    }
}
