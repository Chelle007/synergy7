package src.service;

import java.util.List;

import src.model.entity.Order;
import src.model.entity.OrderDetail;

public interface OrderDetailService {
    // CREATE
    void create(OrderDetail orderDetail);

    // READ
    OrderDetail get(int choice);
    OrderDetail getByChoice(Order order, int choice);
    List<OrderDetail> getList();
    boolean isEmpty();

    // UPDATE
    void update(Order order, int choice, String size, int qty);

    // DELETE
    void deleteByChoice(Order order, int choice);
    void clearList(Order order);
}
