package src;

import lombok.Getter;
import src.model.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Data {
    public static final List<Customer> CUSTOMERS = new ArrayList<>();
    public static final List<MenuItem> MENU_ITEMS = new ArrayList<>();
    public static final List<Restaurant> RESTAURANTS = new ArrayList<>();
    public static final List<Order> ORDERS = new ArrayList<>();
    public static final List<OrderDetail> ORDER_DETAILS = new ArrayList<>();
    public static final List<Promo> PROMOS = new ArrayList<>();
    public static final List<OrderDetail> FREEBIES_ORDER_DETAIL = new ArrayList<>();
    private static final Restaurant restaurant = new Restaurant(0, "BinarResto", "Singapore", true);
    @Getter
    private static final Customer customer = new Customer(0, "Customer", "Customer@gmail.com", "12345678");

    private Data() {
    }

    public static void initializeRestaurants() {
        RESTAURANTS.add(restaurant);
    }

    public static void initializeCustomer() {
        CUSTOMERS.add(customer);
    }

    public static void initializeMenu() {
        MENU_ITEMS.add(new MenuItem(0, restaurant, "Nasi Goreng", MenuItem.FoodType.FOOD, 13000, 15000, 17000));
        MENU_ITEMS.add(new MenuItem(1, restaurant, "Mie Goreng", MenuItem.FoodType.FOOD, 11000, 13000, 15000));
        MENU_ITEMS.add(new MenuItem(2, restaurant, "Nasi + Ayam", MenuItem.FoodType.FOOD, 16000, 18000, 20000));
        MENU_ITEMS.add(new MenuItem(3, restaurant, "Es Teh Manis", MenuItem.FoodType.BEVERAGE, null, 3000, null));
        MENU_ITEMS.add(new MenuItem(4, restaurant, "Es Jeruk", MenuItem.FoodType.BEVERAGE, null, 5000, null));
    }

    public static void initializePromo(Order order) {
        PromoFreebies promoRamadan = new PromoFreebies(0, restaurant, "Ramadan", "Pesan 2 makanan FREE 1 es teh manis!");

        // Set makanan sebagai eligible menu
        List<MenuItem> eligibleMenu = MENU_ITEMS.stream()
                .filter(menuItem -> menuItem.getFoodType() == MenuItem.FoodType.FOOD)
                .toList();

        promoRamadan.setEligibleMenu(eligibleMenu);

        // Set es teh manis sebagai free menu item
        Optional<MenuItem> esTehManisItem = MENU_ITEMS.stream()
                .filter(menuItem -> menuItem.getName().equals("Es Teh Manis"))
                .findFirst();

        esTehManisItem.ifPresent(menuItem -> promoRamadan.setFreeMenuItem(new OrderDetail(ORDERS.size(), order, menuItem, "M", 1, 0)));

        // Set 2 sebagai requirement untuk mendapatkan promo
        promoRamadan.setRequiredMenuItemCount(2);

        // Set waktu berbuka saat Ramadan sebagai valid date dan time
        promoRamadan.setValidStartDate(LocalDate.of(2024, 3, 11));
        promoRamadan.setValidEndDate(LocalDate.of(2024, 4, 10));
        promoRamadan.setValidStartTime(LocalTime.of(17, 0));
        promoRamadan.setValidEndTime(LocalTime.of(21, 0));

        PROMOS.add(promoRamadan);
    }
}
