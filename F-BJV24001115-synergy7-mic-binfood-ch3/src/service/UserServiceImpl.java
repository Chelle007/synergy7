package src.service;

import src.Data;
import src.exception.CustomerNotFoundException;
import src.exception.EmailExistedException;
import src.exception.UsernameExistedException;
import src.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public void create(User user) {
        if (Data.USERS.stream().anyMatch(c -> c.getUsername().equals(user.getUsername()))) {
            throw new UsernameExistedException("Username sudah terpakai: " + user.getUsername());
        } else if (Data.USERS.stream().anyMatch(c -> c.getEmail().equals(user.getEmail()))) {
            throw new EmailExistedException("Akun dengan email yang sama telah dibuat: " + user.getEmail());
        } else {
            user.setId(Data.USERS.size());
            Data.USERS.add(user);
        }
    }

    @Override
    public User getById(int id) {
        Optional<User> customer = Data.USERS.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("User tidak ditemukan: " + id);
        }

        return customer.get();
    }

    @Override
    public User getByUserPass(String username, String password) {
        Optional<User> user = Data.USERS.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

        return user.orElse(null);
    }

    @Override
    public boolean usernameExists(String username) {
        return Data.USERS.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    @Override
    public boolean emailExists(String email) {
        return Data.USERS.stream().anyMatch(u -> u.getEmail().equals(email));
    }

    @Override
    public boolean passwordInvalid(String password) {
        return password.length() < 8;
    }

    @Override
    public List<User> getList() {
        return Data.USERS;
    }

    @Override
    public void delete(int choice) {
        if (choice < 0 || choice >= Data.USERS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Data.USERS.remove(choice);
    }

    @Override
    public void clearList() {
        Data.USERS.clear();
    }
}
