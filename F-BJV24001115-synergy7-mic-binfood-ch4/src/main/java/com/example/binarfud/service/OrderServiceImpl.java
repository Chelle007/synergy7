package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.binarfud.util.AdditionalUtils.*;
import static com.example.binarfud.util.AdditionalUtils.formatBarrier;
import static com.example.binarfud.util.ColorUtils.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired OrderRepository orderRepository;
    @Autowired OrderDetailService orderDetailService;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void createNotes(Order order, String string) {
        order.setNotes(string);
        orderRepository.save(order);
    }

    @Override
    public int getTotalPrice(Order order) {
        Integer totalPrice = orderRepository.getTotalPrice(order.getId());
        return totalPrice != null ? totalPrice : 0;
    }

    @Override
    public int getTotalQty(Order order) {
        Integer totalQty = orderRepository.getTotalQty(order.getId());
        return totalQty != null ? totalQty : 0;
    }

    @Override
    public String getListString(Order order, boolean withColor) {
        StringBuilder output = new StringBuilder();
        String format = "%-19s %-7s %s%n";
        if (withColor) format = "%-21s %-16s %s%n";

        List<OrderDetail> orderDetails = orderDetailService.getByOrder(order);

        for (int i = 0; i< orderDetails.size(); i++) {
            OrderDetail orderDetail = orderDetails.get(i);
            MenuItem menuItem = orderDetail.getMenuItem();
            int price = orderDetail.getPrice();
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
    public List<Order> getByUser(User user) {
        return orderRepository.findByUserAndCompleted(user, true);
    }

    @Override
    public void completeOrder(Order order) {
        order.setOrderTime(LocalDateTime.now());
        order.setCompleted(true);
        orderRepository.save(order);
        log.info("Order {} telah dipesan", order.getId());
    }

    @Override
    public void clearNotes(Order order) {
        order.setNotes("");
        orderRepository.save(order);
    }
}
