package src.test.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Data;
import src.main.java.exception.EmailExistedException;
import src.main.java.exception.UsernameExistedException;
import src.main.java.model.entity.User;
import src.main.java.service.UserService;
import src.main.java.service.UserServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {
    UserService us;
    User user;

    @BeforeEach
    void init() {
        us = new UserServiceImpl();
        user = Data.customer;

        Data.USERS.clear();
        Data.USERS.add(user);
    }

    @Test
    void create() {
        Exception e = assertThrows(
                UsernameExistedException.class, () -> us.create(user));

        assertEquals("Username sudah terpakai: " + user.getUsername(), e.getMessage());
    }

    @Test
    void create2() {
        User user2 = new User(1, "Customer2", "Customer@gmail.com", "12345678", User.Role.CUSTOMER);

        Exception e = assertThrows(
                EmailExistedException.class, () -> us.create(user2));

        assertEquals("Akun dengan email yang sama telah dibuat: " + user.getEmail(), e.getMessage());
    }

    @Test
    void create3() {
        int previousSize = Data.USERS.size();
        User user2 = new User(1, "Customer2", "Customer2@gmail.com", "12345678", User.Role.CUSTOMER);
        us.create(user2);

        assertEquals(previousSize+1, Data.USERS.size());
    }

    @Test
    void getByUserPass() {
        User user2 = us.getByUserPass("blabla", "blabla");

        assertNull(user2);
    }

    @Test
    void usernameExists() {
        boolean ue = us.usernameExists("Customer");

        assertTrue(ue);
    }

    @Test
    void emailExists() {
        boolean ee = us.emailExists("Test@Test.com");

        assertFalse(ee);
    }

    @Test
    void passwordInvalid() {
        boolean pi = us.passwordInvalid("1234");

        assertTrue(pi);
    }

    @Test
    void getList() {
        List<User> users = us.getList();

        assertEquals(Data.USERS, users);
    }
}
