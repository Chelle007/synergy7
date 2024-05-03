package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;

import java.util.List;

public interface OrderDetailService {
    // CREATE
    OrderDetail create(OrderDetail orderDetail);

    // READ
    OrderDetail getByChoice(Order order, int choice);
    List<OrderDetail> getByOrder(Order order);

    // UPDATE
    void update(Order order, int choice, String size, int qty);

    // DELETE
    void safeDeleteByOrderAndChoice(Order order, int choice);
    void safeDeleteAllOrderDetailsByOrder(Order order);
}
