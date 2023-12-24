package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    void updateUser(User user);
    List<User> getAllUsers();
    User readUser(Long id);
    User deleteUser(Long id);
    User findByUsername(String username);
}