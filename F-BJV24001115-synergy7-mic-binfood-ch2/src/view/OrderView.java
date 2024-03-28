package src.view;

import src.controller.OrderController;
import static src.util.ValidationUtils.checkInt;

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
