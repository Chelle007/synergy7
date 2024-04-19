package src.main.java;

import src.main.java.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static final List<User> USERS = new ArrayList<>();
    public static final List<MenuItem> MENU_ITEMS = new ArrayList<>();
    public static final List<Restaurant> RESTAURANTS = new ArrayList<>();
    public static final List<Order> ORDERS = new ArrayList<>();
    public static final List<OrderDetail> ORDER_DETAILS = new ArrayList<>();
    public static final List<OrderDetail> FREEBIES_ORDER_DETAIL = new ArrayList<>();
    public static final Restaurant restaurant = new Restaurant(0, "BinarResto", "Singapore", true);
    public static final User customer = new User(0, "Customer", "Customer@gmail.com", "12345678", User.Role.CUSTOMER, null);

    private Data() {
    }

    public static void initializeRestaurants() {
        RESTAURANTS.add(restaurant);
    }

    public static void initializeCustomer() {
        USERS.add(customer);
    }

    public static void initializeMenu() {
        MENU_ITEMS.add(new MenuItem(0, "Nasi Goreng", MenuItem.FoodType.FOOD, 13000, 15000, 17000, restaurant));
        MENU_ITEMS.add(new MenuItem(1, "Mie Goreng", MenuItem.FoodType.FOOD, 11000, 13000, 15000, restaurant));
        MENU_ITEMS.add(new MenuItem(2, "Nasi + Ayam", MenuItem.FoodType.FOOD, 16000, 18000, 20000, restaurant));
        MENU_ITEMS.add(new MenuItem(3, "Es Teh Manis", MenuItem.FoodType.BEVERAGE, null, 3000, null, restaurant));
        MENU_ITEMS.add(new MenuItem(4, "Es Jeruk", MenuItem.FoodType.BEVERAGE, null, 5000, null, restaurant));
    }
}
