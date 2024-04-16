package src;

import src.controller.UserController;

import static src.Data.*;

public class Main {
    public static void main(String[] args) {
        initializeRestaurants();
        initializeMenu();
        initializeCustomer();
        UserController uc = new UserController();
        uc.displayLoginMenu();
    }
}
