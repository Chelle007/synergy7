package src.view;

import src.model.entity.Order;

import java.util.List;

import static src.util.AdditionalUtils.formatTime;

public class OrderView {
    public void displayOrderList(List<Order> orders) {
        if (orders.isEmpty()) System.out.println("Anda belum memesan apa pun.");
        else {
            displayOrderHeader();
            for (int i = 0; i<orders.size(); i++) {
                Order order = orders.get(i);
                displayOrder(i+1, order);
            }
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
