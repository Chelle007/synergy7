package src.service;

import src.model.entity.Order;

import java.util.List;

public interface OrderService {
    // CREATE
    void add(Order order);
    void createNotes(Order order, String string);

    // READ
    List<Order> getList();
    int getTotalPrice(Order order);
    int getTotalQty(Order order);
    String getListString(Order order, boolean withColor);
    String getTotalListString(Order order, boolean withColor);
    String getReceipt(Order order, boolean withColor, int count);

    // DELETE
    void clearList();
    void clearNotes(Order order);
}
