package com.example.taskList.repository;

import com.example.taskList.domain.task.Task;
import com.example.taskList.domain.user.Role;
import com.example.taskList.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    //return object or Exception if obj not found with help of optional
    Optional<User> findById(Long id);

    //for security check
    Optional<User> findByUsername(String username);

    void update(User user);//if ok thus nothing to return else got Exception

    void create(User user);//create user with id set
    void insertUserRole(Long userId, Role role);//add role to user
    boolean isTaskOwner(Long userId, Task taskId);
    void delete(Long id); //user id
}
