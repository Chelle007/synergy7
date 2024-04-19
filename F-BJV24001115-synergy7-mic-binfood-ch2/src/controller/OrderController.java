package src.main.java.controller;

import src.main.java.service.*;
import static src.main.java.util.AdditionalUtils.formatCurrentTime;
import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;
import static src.main.java.util.ColorUtils.printlnColor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderController {
    public String displayTotalOrderList() {
        OrderService os = new OrderServiceImpl();
        PromoService ps = new PromoServiceImpl();

        ps.resetPromo();
        return os.getTotalListString(true);
    }

    public String displayReceipt() {
        OrderService os = new OrderServiceImpl();

        int order = 1;
        try {
            File receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), order));
            while (receiptFile.exists()) {
                order++;
                receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), order));
            }

            PrintWriter receipt = new PrintWriter(receiptFile);
            receipt.println(os.getReceipt(false, order));
            receipt.close();
        } catch (IOException e) {
            printlnColor("Eror saat menghasilkan struk dalam file text.", COLOR_OF_ERROR);
        }

        return os.getReceipt(true, order);
    }

    public boolean askOrderChoice(int choice) {
        OrderService os = new OrderServiceImpl();

        if (choice >= 0 && choice < os.getList().size()) {
            return true;
        } else {
            printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
        }

        return false;
    }
}
