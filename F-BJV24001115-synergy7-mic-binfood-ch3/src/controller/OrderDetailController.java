package src.controller;

import src.service.*;
import static src.util.AdditionalUtils.formatCurrentTime;
import static src.util.ColorUtils.COLOR_OF_ERROR;
import static src.util.ColorUtils.printlnColor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailController {
    public String displayTotalOrderList() {
        OrderDetailService ods = new OrderDetailServiceImpl();
        PromoService ps = new PromoServiceImpl();

        ps.resetPromo();
        return ods.getTotalListString(true);
    }

    public String displayReceipt() {
        OrderDetailService ods = new OrderDetailServiceImpl();

        int order = 1;
        try {
            File receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), order));
            while (receiptFile.exists()) {
                order++;
                receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), order));
            }

            PrintWriter receipt = new PrintWriter(receiptFile);
            receipt.println(ods.getReceipt(false, order));
            receipt.close();
        } catch (IOException e) {
            printlnColor("Eror saat menghasilkan struk dalam file text.", COLOR_OF_ERROR);
        }

        return ods.getReceipt(true, order);
    }

    public boolean askOrderChoice(int choice) {
        OrderDetailService ods = new OrderDetailServiceImpl();

        if (choice >= 0 && choice < ods.getList().size()) {
            return true;
        } else {
            printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
        }

        return false;
    }
}
