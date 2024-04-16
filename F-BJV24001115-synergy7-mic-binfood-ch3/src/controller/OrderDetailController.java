package src.controller;

import src.model.entity.Order;
import src.service.*;
import src.view.BasicView;

import static src.util.AdditionalUtils.formatCurrentTime;
import static src.util.ColorUtils.COLOR_OF_ERROR;
import static src.util.ValidationUtils.checkInt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailController {
    public int askOrderChoice(Order order) {
        OrderDetailService ods = new OrderDetailServiceImpl();

        do {
            int choice = checkInt("Nomor order: ");
            if (ods.getByChoice(order, choice) != null) {
                return choice;
            } else {
                new BasicView().printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
            }
        } while (true);
    }
}
