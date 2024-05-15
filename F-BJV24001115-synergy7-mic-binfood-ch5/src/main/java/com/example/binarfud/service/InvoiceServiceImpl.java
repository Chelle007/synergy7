//package com.example.binarfud.service;
//
//import com.example.binarfud.model.entity.Order;
//import com.example.binarfud.model.entity.Restaurant;
//import com.example.binarfud.model.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.UUID;
//
//public class InvoiceServiceImpl {
//    @Autowired OrderService orderService;
//    @Autowired UserService userService;
//
//    public Order generateInvoice(UUID userId, UUID orderId) {
//        User user = userService.getById(userId);
//        Order order = orderService.getById(orderId);
//
//        boolean orderBelongsToUser = order.getUser().equals(user);
//        boolean userBalanceIsEnough = user.getBalance() >= order.getPrice();
//
//        if (!orderBelongsToUser) {
//            throw new IllegalArgumentException("Order " + order.getId() + " does not belong to the user " + user.getId() +".");
//        } else if (!order.isCompleted()) {
//            throw new IllegalArgumentException("Order " + order.getId() + " has not completed.");
//        } else if (userBalanceIsEnough) {
//            throw new IllegalArgumentException("User's balance not enough.");
//        } else {
//
//        }
//
//        return order;
//    }
//
//    public Restaurant generateReportingMerchant() {
//
//    }
//}
