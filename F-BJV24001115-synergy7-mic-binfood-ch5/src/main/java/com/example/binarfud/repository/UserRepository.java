package com.example.binarfud.repository;

import com.example.binarfud.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByUsernameAndPassword(String username, String password);

    @Procedure(procedureName = "create_user")
    void createUser(UUID id, String username, String email, String password, String role);

    @Procedure(procedureName = "update_username")
    void updateUsername(UUID id, String username);

    @Procedure(procedureName = "update_email")
    void updateEmail(UUID id, String email);

    @Procedure(procedureName = "update_password")
    void updatePassword(UUID id, String password);

    @Procedure(procedureName = "safe_delete_user")
    void safeDeleteUser(UUID id);
}
