package src.main.java.service;

import src.main.java.Data;
import src.main.java.model.entity.*;

import java.time.LocalDateTime;
import java.util.List;

import static src.main.java.util.AdditionalUtils.*;
import static src.main.java.util.AdditionalUtils.formatBarrier;
import static src.main.java.util.ColorUtils.*;

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

        return output.toString().replace("\r", "");
    }

    @Override
    public String getReceipt(Order order, boolean withColor, int count) {
        String output =  formatBarrier("BinarFud") +
                "Waktu : " + formatTime("yyyy-MM-dd HH:mm:ss", LocalDateTime.now()) + "\n" +
                "Order : " + count + "\n" +
                """
                
                Terima kasih sudah memesan di BinarFud

                Di bawah ini adalah pesanan anda
                
                """ +
                getTotalListString(order, withColor) +
                """
                Pembayaran : BinarCash
                
                ** Simpan struk ini sebagai bukti pembayaran **""";

        return output.replace("\r", "");
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
