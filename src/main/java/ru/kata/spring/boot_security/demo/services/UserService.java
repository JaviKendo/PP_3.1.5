package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> listAll();

    void save(User user);

    User get(Long id);

    void delete(Long id);

//    String getEncodedPasswordBCrypt(String password);

}