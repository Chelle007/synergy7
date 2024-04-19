package src.main.java;

import src.main.java.controller.UserController;
import src.main.java.model.entity.*;
import src.main.java.service.OrderService;
import src.main.java.service.OrderServiceImpl;

import static src.main.java.Data.*;

public class Main {
    public static void main(String[] args) {
        initializeRestaurants();
        initializeMenu();
        initializeCustomer();
        UserController uc = new UserController();
        uc.displayLoginMenu();
    }
}
