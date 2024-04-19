package src.main.java.view;

import src.main.java.controller.OrderController;
import static src.main.java.util.ValidationUtils.checkInt;

public class OrderView {
    public int askOrderChoice() {
        OrderController oc = new OrderController();

        int choice;
        do {
            choice = checkInt("Nomor order: ");
        } while (!oc.askOrderChoice(choice - 1));

        return choice;
    }
}
