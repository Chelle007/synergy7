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
        Optional<User> customer = Data.USERS.stream()
                .filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
                .findFirst();

        return customer.orElse(null);
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
