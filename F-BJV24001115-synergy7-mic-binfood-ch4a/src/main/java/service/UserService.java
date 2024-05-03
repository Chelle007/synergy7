package src.main.java.service;

import src.main.java.model.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);
    User getByUserPass(String username, String password);
    List<User> getList();
    boolean usernameExists(String username);
    boolean emailExists(String email);
    boolean passwordInvalid(String password);
}
