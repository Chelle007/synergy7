package src.main.java.controller;

import src.main.java.model.entity.Order;
import src.main.java.service.OrderService;
import src.main.java.service.OrderServiceImpl;
import src.main.java.view.BasicView;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import static src.main.java.util.AdditionalUtils.formatTime;
import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;

public class OrderController {
    public String displayTotalOrderList(Order order) {
        OrderService os = new OrderServiceImpl();
        return os.getTotalListString(order, true);
    }

    public String displayReceipt(Order order) {
        OrderService os = new OrderServiceImpl();
        BasicView bv = new BasicView();

        int count = 1;
        try {
            File receiptFile = new File(String.format("receipt(%s-%04d).txt", formatTime("yyyy-MMdd", LocalDateTime.now()), count));
            while (receiptFile.exists()) {
                count++;
                receiptFile = new File(String.format("receipt(%s-%04d).txt", formatTime("yyyy-MMdd", LocalDateTime.now()), count));
            }

            PrintWriter receipt = new PrintWriter(receiptFile);
            receipt.println(os.getReceipt(order, false, count));
            receipt.close();
        } catch (IOException e) {
            bv.printlnColor("Eror saat menghasilkan struk dalam file text.", COLOR_OF_ERROR);
        }

        return os.getReceipt(order, true, count);
    }
}
