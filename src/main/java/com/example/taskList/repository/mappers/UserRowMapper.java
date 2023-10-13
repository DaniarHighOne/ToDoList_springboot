package com.example.taskList.repository.mappers;

import com.example.taskList.domain.user.Role;
import com.example.taskList.domain.user.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class UserRowMapper {
    @SneakyThrows
    public static User mapRow(ResultSet resultSet) {
        Set<Role> roles = new HashSet<>();
        while(resultSet.next()) {
            roles.add(Role.valueOf(resultSet.getString("user_role_role")));
        }
    }
}
