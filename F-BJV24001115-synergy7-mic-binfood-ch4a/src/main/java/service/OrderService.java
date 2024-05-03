package src.main.java.service;

import src.main.java.model.entity.Order;

public interface OrderService {
    // CREATE
    void add(Order order);
    void createNotes(Order order, String string);

    // READ
    int getTotalPrice(Order order);
    int getTotalQty(Order order);
    String getListString(Order order, boolean withColor);
    String getTotalListString(Order order, boolean withColor);
    String getReceipt(Order order, boolean withColor, int count);

    // DELETE
    void clearList();
    void clearNotes(Order order);
}
