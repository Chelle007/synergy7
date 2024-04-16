package src.controller;

import src.model.entity.Order;
import src.service.OrderService;
import src.service.OrderServiceImpl;
import src.view.BasicView;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static src.util.AdditionalUtils.formatCurrentTime;
import static src.util.ColorUtils.COLOR_OF_ERROR;

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
            File receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), count));
            while (receiptFile.exists()) {
                count++;
                receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), count));
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
