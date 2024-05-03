package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;

import java.util.List;

public interface OrderService {
    // CREATE
    Order create(Order order);
    void createNotes(Order order, String string);

    // READ
    int getTotalPrice(Order order);
    int getTotalQty(Order order);
    String getListString(Order order, boolean withColor);
    String getTotalListString(Order order, boolean withColor);
    String getReceipt(Order order, boolean withColor, int count);
    List<Order> getByUser(User user);

    // UPDATE
    void completeOrder(Order order);

    // DELETE
    void clearNotes(Order order);
}
