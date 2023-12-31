package com.example.taskList.repository.impl;

import com.example.taskList.domain.exception.ResourceMappingException;
import com.example.taskList.domain.user.Role;
import com.example.taskList.domain.user.User;
import com.example.taskList.repository.DataSourceConfig;
import com.example.taskList.repository.UserRepository;
import com.example.taskList.repository.mappers.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository//with the help of Autowired we can use it elsewhere
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            SElECT u.id as user_id,
                   u.name as user_name,
                   u.username as user_username,
                   u.password as user_password,
                   ur.role as user_role_role,
                   t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            from users u
                LEFT JOIN users_role ur on u.id = ur.user_id
                LEFT JOIN users_tasks ut on u.id = ut.user_id
                LEFT JOIN tasks t on ut.task_id = t.id
            WHERE u.id = ?
            """;
    private final String FIND_BY_USERNAME = """
            SElECT u.id as user_id,
                   u.name as user_name,
                   u.username as user_username,
                   u.password as user_password,
                   ur.role as user_role_role,
                   t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            from users u
                LEFT JOIN users_role ur on u.id = ur.user_id
                LEFT JOIN users_tasks ut on u.id = ut.user_id
                LEFT JOIN tasks t on ut.task_id = t.id
            WHERE u.username = ?
            """;

    private final String UPDATE = """
            UPDATE users
            SET name = ?,
                username = ?,
                password = ?
            WHERE id = ?;   
            """;

    private final String CREATE = """
            INSERT INTO users (name, username, password)
            VALUES (?, ?, ?)
            """;

    private final String INSERT_USER_ROLE = """
            INSERT INTO users_role (user_id, role)
            VALUES (?, ?)
            """;
    private final String IS_TASK_OWNER = """
            SELECT exists(
                SELECT 1
                FROM users_tasks
                WHERE user_id = ?
                AND task_id = ?
            )
            """;

    private final String DELETE = """
            DELETE from users
            WHERE id = ?
            """;

    @Override
    public Optional<User> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID,
                    ResultSet.TYPE_SCROLL_SENSITIVE,//look up whole result set
                    ResultSet.CONCUR_READ_ONLY);
            statement.setLong(1,id);
            try (ResultSet rs = statement.executeQuery()){
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while finding user by id");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME,
                    ResultSet.TYPE_SCROLL_SENSITIVE,//look up whole result set
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1,username);
            try (ResultSet rs = statement.executeQuery()){
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while finding user by username");
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            //need to set all fields
            statement.setString(1,user.getName());
            statement.setString(2,user.getUsername());
            statement.setString(3,user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while updating user!");
        }

    }

    @Override
    public void create(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            //need to set all fields
            statement.setString(1,user.getName());
            statement.setString(2,user.getUsername());
            statement.setString(3,user.getPassword());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()){
                rs.next();
                user.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while creating user!");
        }

    }

    @Override
    public void insertUserRole(Long userId, Role role) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE);
            //need to set all fields
            statement.setLong(1,userId);
            statement.setString(2,role.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while add role to user!");
        }

    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_TASK_OWNER);
            //need to set all fields
            statement.setLong(1,userId);
            statement.setLong(2,taskId);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while checking task own-age user!");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            //need to set all fields
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while deleting user!");
        }
    }
}
