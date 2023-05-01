package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> findUserById(Long id);

    User getUserById(Long id);

    void addNewUser(User user);

    void updateUser(User updatedUser);

    void removeUserById(Long id);

}