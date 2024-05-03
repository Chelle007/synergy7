package src.main.java;

import src.main.java.controller.UserController;

import static src.main.java.Data.*;

public class Main {
    public static void main(String[] args) {
        initializeRestaurants();
        initializeMenu();
        initializeCustomer();
        initializeSeller();
        UserController uc = new UserController();
        uc.displayLoginMenu();
    }
}
