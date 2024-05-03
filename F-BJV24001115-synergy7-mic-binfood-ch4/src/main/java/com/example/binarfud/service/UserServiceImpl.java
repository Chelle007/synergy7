package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.exception.*;
import com.example.binarfud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        if (usernameExists(user.getUsername())) {
            throw new UsernameExistedException("Username sudah terpakai: " + user.getUsername());
        } else if (emailExists(user.getEmail())) {
            throw new EmailExistedException("Akun dengan email yang sama telah dibuat: " + user.getEmail());
        }

        user.setId(UUID.randomUUID());
        userRepository.createUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().toString());
        log.info("User {} berhasil ditambahkan", user.getId());
        return user;
    }

    @Override
    public User getByUserPass(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean passwordInvalid(String password) {
        return password.length() < 8;
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public User.Role convertStringToRole(String role) {
        for (User.Role enumValue : User.Role.values()) {
            if (enumValue.name().equalsIgnoreCase(role)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Role invalid: " + role);
    }

    @Override
    public void updateUsername(User user, String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameExistedException("Username sudah terpakai: " + username);
        }
        user.setUsername(username);
        userRepository.updateUsername(user.getId(), username);
        log.info("Username user {} telah diganti ke {}", user.getId(), username);
    }

    @Override
    public void updateEmail(User user, String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailExistedException("Akun dengan email yang sama telah dibuat: " + email);
        }
        user.setEmail(email);
        userRepository.updateEmail(user.getId(), email);
        log.info("Email user {} telah diganti ke {}", user.getId(), email);
    }

    @Override
    public void updatePassword(User user, String password) {
        user.setPassword(password);
        userRepository.updatePassword(user.getId(), password);
        log.info("Password user {} telah diganti", user.getId());
    }

    @Override
    public void safeDeleteUser(User user) {
        user.setDeleted(true);
        userRepository.safeDeleteUser(user.getId());
        log.info("Akun user {} telah dihapus", user.getId());
    }
}
