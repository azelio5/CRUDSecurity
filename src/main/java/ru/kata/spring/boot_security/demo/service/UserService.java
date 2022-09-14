package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {


    List<User> getAllUsers ();
    User getUserById(int id);
    void addUser(User user);
    void removeUser(int id);
    void updateUser(User user);
    User getUserByLogin(String username);
}
