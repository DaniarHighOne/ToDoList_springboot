package com.example.taskList.repository.impl;

import com.example.taskList.domain.exception.ResourceMappingException;
import com.example.taskList.domain.task.Task;
import com.example.taskList.repository.DataSourceConfig;
import com.example.taskList.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID =
            """
                SElECT t.id as task_id,
                    t.title as task_title,
                    t.description as task_description,
                    t.expiration_date  as task_expiration_date,
                    t.status as task_status
                from tasks t
                where id = ?
            """;
    private final String FIND_ALL_BY_USER_ID =
            """
            SElECT t.id as task_id,
                    t.title as task_title,
                    t.description as task_description,
                    t.expiration_date  as task_expiration_date,
                    t.status as task_status
            from tasks t
                    JOIN users_tasks ut on t.id = ut.task_id
            where ut.user_id = ?
            """;

    private final String ASSIGN =
            """
            INSERT INTO users_tasks (task_id, user_id)
            VALUES(?, ?)
            """;
    private final String CREATE =
            """
            INSERT INTO tasks (title, description, expiration_date, status)
            VALUES (?, ?, ?, ?)
            """;
    private final String DELETE =
            """
            DELETE FROM tasks
            WHERE id = ?
            """;
    private final String UPDATE =
            """
            UPDATE tasks
            SET title = ?,
                description = ?,
                expiration_date = ?,
                status = ?
            WHERE id = ?
            """;


    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try
                (ResultSet rs = statement.executeQuery()) {
                    return Optional.ofNullable(TaskRowMapper.mapRow(rs));
                }
        } catch (SQLDataException throwables) {
            throw new ResourceMappingException("Error while finding task by id")
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void assignByUserID(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void delete(Long id) {

    }
}
