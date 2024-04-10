package src.service;

import src.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    // CREATE
    void create(Customer customer);

    // READ
    Customer getById(int choice);
    List<Customer> getList();

    // DELETE
    void delete(int choice);
    void clearList();
}
