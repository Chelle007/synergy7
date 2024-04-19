package src.main.java.service;

import src.main.java.model.entity.Order;
import src.main.java.model.entity.OrderDetail;

public interface OrderDetailService {
    // CREATE
    void create(OrderDetail orderDetail);

    // READ
    OrderDetail getByChoice(Order order, int choice);

    // UPDATE
    void update(Order order, int choice, String size, int qty);

    // DELETE
    void deleteByChoice(Order order, int choice);
    void clearList(Order order);
}
