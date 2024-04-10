package src.view;

import src.controller.OrderDetailController;

import static src.util.ValidationUtils.checkInt;

public class OrderView {
    public int askOrderChoice() {
        OrderDetailController oc = new OrderDetailController();

        int choice;
        do {
            choice = checkInt("Nomor order: ");
        } while (!oc.askOrderChoice(choice - 1));

        return choice;
    }
}
