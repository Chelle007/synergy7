package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;

import java.util.List;

public interface UserService {
    // CREATE
    User create(User user);

    // READ
    User getByUserPass(String username, String password);
    List<User> getList();
    boolean usernameExists(String username);
    boolean emailExists(String email);
    boolean passwordInvalid(String password);
    User.Role convertStringToRole(String role);

    // UPDATE
    void updateUsername(User user, String name);
    void updateEmail(User user, String email);
    void updatePassword(User user, String password);

    // DELETE
    void safeDeleteUser(User user);
}
