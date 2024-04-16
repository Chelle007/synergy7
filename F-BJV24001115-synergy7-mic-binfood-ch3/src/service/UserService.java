package src.service;

import src.model.entity.User;

import java.util.List;

public interface UserService {
    // CREATE
    void create(User user);

    // READ
    User getById(int choice);
    User getByUserPass(String username, String password);
    List<User> getList();
    boolean usernameExists(String username);
    boolean emailExists(String email);
    boolean passwordInvalid(String password);

    // DELETE
    void delete(int choice);
    void clearList();
}
