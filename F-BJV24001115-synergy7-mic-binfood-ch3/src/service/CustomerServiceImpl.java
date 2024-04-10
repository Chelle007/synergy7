package src.service;

import src.Data;
import src.exception.CustomerNotFoundException;
import src.exception.EmailExistedException;
import src.exception.UsernameExistedException;
import src.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public void create(Customer customer) {
        if (Data.CUSTOMERS.stream().anyMatch(c -> c.getUsername().equals(customer.getUsername()))) {
            throw new UsernameExistedException("Username sudah terpakai: " + customer.getUsername());
        } else if (Data.CUSTOMERS.stream().anyMatch(c -> c.getEmail().equals(customer.getEmail()))) {
            throw new EmailExistedException("Akun dengan email yang sama telah dibuat: " + customer.getEmail());
        } else {
            customer.setId(Data.CUSTOMERS.size());
            Data.CUSTOMERS.add(customer);
        }
    }

    @Override
    public Customer getById(int id) {
        Optional<Customer> customer = Data.CUSTOMERS.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Merchant tidak ditemukan: " + id);
        }

        return customer.get();
    }

    @Override
    public List<Customer> getList() {
        return Data.CUSTOMERS;
    }

    @Override
    public void delete(int choice) {
        if (choice < 0 || choice >= Data.CUSTOMERS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Data.CUSTOMERS.remove(choice);
    }

    @Override
    public void clearList() {
        Data.CUSTOMERS.clear();
    }
}
