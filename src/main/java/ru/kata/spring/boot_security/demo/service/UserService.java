package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    List<User> getAllUsers();
    User readUser(Long id);
    User deleteUser(Long parseUnsignedInt);
    User findByUsername(String username);
}
