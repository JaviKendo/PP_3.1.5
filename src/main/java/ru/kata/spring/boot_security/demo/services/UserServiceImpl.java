package ru.kata.spring.boot_security.demo.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserDetailsImpl(user.get());
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    @Query("select u from User u left join fetch u.roles where u.username=:username")
    public void save(User newOrChangedUser) {
        Optional<User> user = userRepository.getUserByUsername(newOrChangedUser.getUsername());
        String newPassword = newOrChangedUser.getPassword();
        String currentPassword = "";

        if (user.isPresent()) {
            currentPassword = user.get().getPassword();
        }

        if (!currentPassword.equals(newPassword)) {
            newOrChangedUser.setPassword(getEncodedPasswordBCrypt(newPassword));
        }

        userRepository.save(newOrChangedUser);
    }

    @Override
    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        return user.get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String getEncodedPasswordBCrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}