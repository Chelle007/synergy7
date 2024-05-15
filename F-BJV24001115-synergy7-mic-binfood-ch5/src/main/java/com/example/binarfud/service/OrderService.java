package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    // CREATE
    Order create(Order order);
    void createNotes(Order order, String string);

    // READ
    Order getById(UUID orderId);
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
