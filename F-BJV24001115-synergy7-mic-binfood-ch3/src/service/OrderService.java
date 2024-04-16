package src.service;

import src.model.entity.Order;
import src.model.entity.OrderDetail;
import src.model.entity.Restaurant;
import src.model.entity.User;

import java.util.List;

public interface OrderService {
    // CREATE
    void add(Order order);
    void createNotes(Order order, String string);

    // READ
    Order getById(int choice);
    List<Order> getList();
    int getTotalPrice(Order order);
    int getTotalQty(Order order);
    String getListString(Order order, boolean withColor);
    String getTotalListString(Order order, boolean withColor);
    String getReceipt(Order order, boolean withColor, int count);

    // DELETE
    void deleteById(int choice);
    void clearList();
    void clearNotes(Order order);
}
