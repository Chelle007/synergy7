package src;

import src.model.entity.Order;
import src.view.CustomerView;
import src.view.ProfileView;

import java.time.LocalDateTime;

import static src.Data.*;

public class Main {
    public static void main(String[] args) {
        initializeRestaurants();
        initializeMenu();
        initializeCustomer();
        initializePromo(new Order(0, LocalDateTime.now(), "Singapore", Data.getCustomer(), false));
        ProfileView prv = new ProfileView();
        prv.displayLoginMenu();
    }
}
