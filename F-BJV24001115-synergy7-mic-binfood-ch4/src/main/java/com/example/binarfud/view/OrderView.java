package com.example.binarfud.view;

import com.example.binarfud.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.binarfud.util.AdditionalUtils.formatTime;

@Component
public class OrderView {
    public void displayOrderList(List<Order> orders) {
        if (orders.isEmpty()) System.out.println("Anda belum memesan apa pun.");
        else {
            displayOrderHeader();
            AtomicInteger index = new AtomicInteger(1);
            orders.forEach(order -> displayOrder(index.getAndIncrement(), order));
        }
    }

    public void displayOrderHeader() {
        System.out.println("     Restaurant     |     Waktu Order");
    }

    public void displayOrder(int num, Order order) {
        System.out.printf(
                "%d. %-16s | %s%n",
                num,
                order.getRestaurant().getName(),
                formatTime("yyyy-MM-dd HH:mm:ss", order.getOrderTime())
        );
    }
}
