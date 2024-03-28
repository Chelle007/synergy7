package src.service;

import java.util.ArrayList;

import src.model.entity.Order;

public interface OrderService {
    // CREATE
    void create(Order order);

    // READ
    Order get(int choice);
    ArrayList<Order> getList();
    boolean isEmpty();
    int getTotalPrice();
    int getTotalQty();
    String getListString(boolean withColor, ArrayList<Order> orders);
    String getTotalListString(boolean withColor);
    String getReceipt(boolean withColor, int order);

    // UPDATE
    void update(int choice, String size, int qty);

    // DELETE
    void delete(int choice);
    void clearList();
}
