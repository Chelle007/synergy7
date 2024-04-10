package src.service;

import src.Data;
import src.exception.OrderDetailNotFoundException;
import src.model.entity.MenuItem;
import src.model.entity.OrderDetail;

import static src.util.AdditionalUtils.*;
import static src.util.ColorUtils.*;

import java.util.List;
import java.util.Optional;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public void create(OrderDetail orderDetail) {
        Optional<OrderDetail> existingOrderDetail = Data.ORDER_DETAILS.stream()
                .filter(od -> od.getMenuItem().equals(orderDetail.getMenuItem()) && od.getSize().equals(orderDetail.getSize()))
                .findFirst();

        if (existingOrderDetail.isPresent()) {
            existingOrderDetail.get().addQty(orderDetail.getQty());
        } else {
            Data.ORDER_DETAILS.add(orderDetail);
        }
    }

    @Override
    public OrderDetail get(int choice) {
        if (choice < 0 || choice >= Data.ORDER_DETAILS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        OrderDetail orderDetail = Data.ORDER_DETAILS.get(choice);
        if (orderDetail == null) {
            throw new OrderDetailNotFoundException("Order tidak ditemukan: " + choice);
        }

        return orderDetail;
    }

    @Override
    public List<OrderDetail> getList() {
        return Data.ORDER_DETAILS;
    }

    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public int getTotalPrice() {
        int totalPrice = 0;

        for (OrderDetail orderDetail : getList()) {
            totalPrice += orderDetail.getPrice() * orderDetail.getQty();
        }

        return totalPrice;
    }

    @Override
    public int getTotalQty() {
        int totalQty = 0;

        for (OrderDetail orderDetail : getList()) {
            totalQty += orderDetail.getQty();
        }

        return totalQty;
    }

    @Override
    public String getListString(boolean withColor, List<OrderDetail> orderDetails) {
        StringBuilder output = new StringBuilder();
        String format = "%-19s %-7s %s%n";
        if (withColor) format = "%-21s %-16s %s%n";

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
    public String getTotalListString(boolean withColor) {
        StringBuilder output = new StringBuilder();

        output.append(getListString(withColor, Data.ORDER_DETAILS));

        if (!Data.FREEBIES_ORDER_DETAIL.isEmpty()) {
            output.append("FREE\n");
            output.append(getListString(withColor, Data.FREEBIES_ORDER_DETAIL));
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
        OrderDetail orderDetail = get(choice);

        if (orderDetail.getSize().equals(size)) {
            orderDetail.setQty(qty);
        } else {
//            OrderDetail updatedOrderDetail = new OrderDetail(orderDetail.getMenuItem(), size, qty);
//            Data.ORDER_DETAILS.remove(orderDetail);
//            create(updatedOrderDetail);
        }
    }

    @Override
    public void delete(int choice) {
        if (choice < 0 || choice >= Data.ORDER_DETAILS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Data.ORDER_DETAILS.remove(choice);
    }

    @Override
    public void clearList() {
        Data.ORDER_DETAILS.clear();
    }
}
