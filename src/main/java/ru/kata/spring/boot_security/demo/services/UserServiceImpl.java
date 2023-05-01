package ru.kata.spring.boot_security.demo.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("There is no user with username - %s in database", user));
        }

        return user.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException(String.format("There is no user with ID = %d in database", id));
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException(String.format("There is no user with ID = %d in database", id));
        }

        return user.get();
    }

    @Override
    @Transactional
    public void addNewUser(User newUser) {
        newUser.setUsername(newUser.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        updatedUser.setUsername(updatedUser.getEmail());

        if (!updatedUser.getPassword().equals(this.getUserById(updatedUser.getId()).getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

}