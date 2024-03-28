package src.service;

import src.Data;
import src.exception.OrderNotFoundException;
import src.model.entity.MenuItem;
import src.model.entity.Order;

import java.util.ArrayList;

import static src.util.AdditionalUtils.*;
import static src.util.ColorUtils.*;

public class OrderServiceImpl implements OrderService {
    @Override
    public void create(Order order) {
        boolean found = false;

        for (Order o : Data.orders) {
            if (o.getMenuItem().equals(order.getMenuItem()) && o.getSize().equals(order.getSize())) {
                found = true;
                o.addQty(order.getQty());
                break;
            }
        }

        if (!found) Data.orders.add(order);
    }

    @Override
    public Order get(int choice) {
        if (choice < 0 || choice >= Data.orders.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Order order = Data.orders.get(choice);
        if (order == null) {
            throw new OrderNotFoundException("Order tidak ditemukan: " + choice);
        }

        return order;
    }

    @Override
    public ArrayList<Order> getList() {
        return Data.orders;
    }

    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public int getTotalPrice() {
        int totalPrice = 0;

        for (Order order : getList()) {
            totalPrice += order.getPrice() * order.getQty();
        }

        return totalPrice;
    }

    @Override
    public int getTotalQty() {
        int totalQty = 0;

        for (Order order : getList()) {
            totalQty += order.getQty();
        }

        return totalQty;
    }

    @Override
    public String getListString(boolean withColor, ArrayList<Order> orders) {
        StringBuilder output = new StringBuilder();
        String format = "%-19s %-7s %s%n";
        if (withColor) format = "%-21s %-16s %s%n";

        for (int i = 0; i<orders.size(); i++) {
            Order order = orders.get(i);
            MenuItem menuItem = order.getMenuItem();
            int price = order.getPrice()*order.getQty();
            String qty = Integer.toString(order.getQty());

            output.append(String.format(
                    format,
                    (i+1) + ". " + menuItem.getName() + " (" + order.getSize() + ")",
                    withColor ? formatColor(qty, COLOR_OF_QTY) : qty,
                    withColor ? formatColor(formatPrice(price), COLOR_OF_PRICE) : formatPrice(price)
            ));
        }

        return output.toString();
    }

    @Override
    public String getTotalListString(boolean withColor) {
        StringBuilder output = new StringBuilder();

        output.append(getListString(withColor, Data.orders));

        if (!Data.freebiesOrder.isEmpty()) {
            output.append("FREE\n");
            output.append(getListString(withColor, Data.freebiesOrder));
        }

        output.append("-".repeat(35));
        output.append("+\n");

        int totalQty = getTotalQty();
        int totalPrice = getTotalPrice();
        output.append(String.format(
                withColor ? "%-21s %-16s %s%n" : "%-19s %-7s %s%n",
                "Total ",
                withColor ? formatColor(Integer.toString(totalQty), COLOR_OF_QTY) : Integer.toString(totalQty),
                withColor ? formatColor(formatPrice(totalPrice), COLOR_OF_PRICE) : formatPrice(totalPrice)
        ));

        return output.toString();
    }

    @Override
    public String getReceipt(boolean withColor, int order) {
        return formatBarrier("BinarFud") +
                "Waktu : " + formatCurrentTime("yyyy-MM-dd HH:mm:ss") + "\n" +
                "Order : " + order + "\n" +
                """
                
                Terima kasih sudah memesan
                di BinarFud

                Di bawah ini adalah pesanan anda
                
                """ +
                getTotalListString(withColor) +
                "\nPembayaran : BinarCash\n" +
                formatBarrier("Simpan struk ini sebagai bukti pembayaran");
    }

    @Override
    public void update(int choice, String size, int qty) {
        Order order = get(choice);

        if (order.getSize().equals(size)) {
            order.setQty(qty);
        } else {
            Order updatedOrder = new Order(order.getMenuItem(), size, qty);
            Data.orders.remove(order);
            create(updatedOrder);
        }
    }

    @Override
    public void delete(int choice) {
        if (choice < 0 || choice >= Data.orders.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Data.orders.remove(choice);
    }

    @Override
    public void clearList() {
        Data.orders.clear();
    }
}
