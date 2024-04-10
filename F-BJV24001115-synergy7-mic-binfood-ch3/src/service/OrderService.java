package src.service;

import src.model.entity.Order;

import java.util.List;

public interface OrderService {
    // CREATE
    void create(Order order);

    // READ
    Order getById(int choice);
    List<Order> getList();

    // DELETE
    void deleteById(int choice);
    void clearList();
}
