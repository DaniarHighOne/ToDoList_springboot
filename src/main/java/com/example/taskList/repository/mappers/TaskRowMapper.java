package com.example.taskList.repository.mappers;

import com.example.taskList.domain.task.Status;
import com.example.taskList.domain.task.Task;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskRowMapper {
    //get the set of sql request and return an object
    @SneakyThrows//annotation for throwing exceptions in up to main class
    public static Task mapRow(ResultSet resultSet) {
        if(resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            task.setTitle(resultSet.getString("task_title"));
            task.setDescription(resultSet.getString("task_description"));
            task.setStatus(Status.valueOf(resultSet.getString("task_status")));
            Timestamp timestamp = resultSet.getTimestamp("task_expiration_date");
            if (timestamp != null) {//check on day in null
                task.setExpirationDate(timestamp.toLocalDateTime());
            }
            return task;
        }
        return null;
    }

    @SneakyThrows//annotation for throwing exceptions in up to main class
    public static List<Task> mapRows(ResultSet resultSet) {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            if (!resultSet.wasNull()) {//for checking on null by id
                task.setTitle(resultSet.getString("task_title"));
                task.setDescription(resultSet.getString("task_description"));
                task.setStatus(Status.valueOf(resultSet.getString("task_status")));
                Timestamp timestamp = resultSet.getTimestamp("task_expiration_date");
                if (timestamp != null) {//check on daye in null
                    task.setExpirationDate(timestamp.toLocalDateTime());
                }
                tasks.add(task);
            }
        }
        return tasks;
    }
}
