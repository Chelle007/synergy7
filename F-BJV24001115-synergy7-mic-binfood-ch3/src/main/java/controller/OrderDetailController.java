package src.main.java.controller;

import src.main.java.model.entity.Order;
import src.main.java.service.*;
import src.main.java.view.BasicView;

import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;
import static src.main.java.view.ValidationView.checkInt;

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
