package src.view;

import java.util.ArrayList;

import src.controller.OrderController;
import src.controller.PromoController;
import src.model.entity.Order;

import static src.util.ColorUtils.COLOR_OF_ERROR;
import static src.util.ColorUtils.printlnColor;
import static src.util.ValidationUtils.checkInt;

public class OrderView {
    public int askOrderChoice() {
        OrderController oc = new OrderController();

        int choice = 0;
        while (true) {
            choice = checkInt("Nomor order: ");
            if (oc.askOrderChoice(choice-1)) break;
        }

        return choice;
    }
}
