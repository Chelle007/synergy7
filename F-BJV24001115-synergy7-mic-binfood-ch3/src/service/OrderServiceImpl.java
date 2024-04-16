package src.service;

import src.Data;
import src.exception.OrderNotFoundException;
import src.model.entity.*;

import java.util.List;
import java.util.Optional;

import static src.util.AdditionalUtils.*;
import static src.util.AdditionalUtils.formatBarrier;
import static src.util.ColorUtils.*;

public class OrderServiceImpl implements OrderService {
    @Override
    public void add(Order order) {
        order.setId(Data.ORDERS.size());
        Data.ORDERS.add(order);
    }

    @Override
    public void createNotes(Order order, String string) {
        order.setNotes(string);
    }

    @Override
    public Order getById(int id) {
        Optional<Order> order = Data.ORDERS.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order tidak ditemukan: " + id);
        }

        return order.get();
    }

    @Override
    public List<Order> getList() {
        return Data.ORDERS;
    }

    @Override
    public int getTotalPrice(Order order) {
        int totalPrice = 0;

        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            totalPrice += orderDetail.getPrice() * orderDetail.getQty();
        }

        return totalPrice;
    }

    @Override
    public int getTotalQty(Order order) {
        int totalQty = 0;

        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            totalQty += orderDetail.getQty();
        }

        return totalQty;
    }

    @Override
    public String getListString(Order order, boolean withColor) {
        StringBuilder output = new StringBuilder();
        String format = "%-19s %-7s %s%n";
        if (withColor) format = "%-21s %-16s %s%n";

        List<OrderDetail> orderDetails = order.getOrderDetailList();

        for (int i = 0; i< orderDetails.size(); i++) {
            OrderDetail orderDetail = orderDetails.get(i);
            MenuItem menuItem = orderDetail.getMenuItem();
            int price = orderDetail.getPrice()* orderDetail.getQty();
            String qty = Integer.toString(orderDetail.getQty());

            output.append(String.format(
                    format,
                    (i+1) + ". " + menuItem.getName() + " (" + orderDetail.getSize() + ")",
                    withColor ? formatColor(qty, COLOR_OF_QTY) : qty,
                    withColor ? formatColor(formatPrice(price), COLOR_OF_PRICE) : formatPrice(price)
            ));
        }

        return output.toString();
    }

    @Override
    public String getTotalListString(Order order, boolean withColor) {
        StringBuilder output = new StringBuilder();

        output.append(getListString(order, withColor));

        if (!Data.FREEBIES_ORDER_DETAIL.isEmpty()) {
            output.append("FREE\n");
            output.append(getListString(order, withColor));
        }

        output.append("-".repeat(35));
        output.append("+\n");

        int totalQty = getTotalQty(order);
        int totalPrice = getTotalPrice(order);
        output.append(String.format(
                withColor ? "%-21s %-16s %s%n" : "%-19s %-7s %s%n",
                "Total ",
                withColor ? formatColor(Integer.toString(totalQty), COLOR_OF_QTY) : Integer.toString(totalQty),
                withColor ? formatColor(formatPrice(totalPrice), COLOR_OF_PRICE) : formatPrice(totalPrice)
        ));

        return output.toString();
    }

    @Override
    public String getReceipt(Order order, boolean withColor, int count) {
        return formatBarrier("BinarFud") +
                "Waktu : " + formatCurrentTime("yyyy-MM-dd HH:mm:ss") + "\n" +
                "Order : " + count + "\n" +
                """
                
                Terima kasih sudah memesan di BinarFud

                Di bawah ini adalah pesanan anda
                
                """ +
                getTotalListString(order, withColor) +
                """
                Pembayaran : BinarCash
                
                ** Simpan struk ini sebagai bukti pembayaran **""";
    }

    @Override
    public void deleteById(int id) {
        Order order = getById(id);
        Data.ORDERS.remove(order);
    }

    @Override
    public void clearList() {
        Data.ORDERS.clear();
    }

    @Override
    public void clearNotes(Order order) {
        order.setNotes("");
    }
}
