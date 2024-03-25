package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import lombok.Getter;

import model.entity.*;

public class InitializationUtils {
    @Getter private static final ArrayList<MenuItem> menu = new ArrayList<>();
    @Getter private static final ArrayList<Order> orders = new ArrayList<>();
    @Getter private static final ArrayList<Promo> promos = new ArrayList<>();
    @Getter private static final ArrayList<Order> freebiesOrder = new ArrayList<>();

    private InitializationUtils() {
    }

    public static void initializeMenu() {
        menu.add(new MenuItem("Nasi Goreng", MenuItem.FoodType.FOOD, 15000, 2000));
        menu.add(new MenuItem("Mie Goreng", MenuItem.FoodType.FOOD, 13000, 2000));
        menu.add(new MenuItem("Nasi + Ayam", MenuItem.FoodType.FOOD, 18000, 2000));
        menu.add(new MenuItem("Es Teh Manis", MenuItem.FoodType.BEVERAGE, 3000));
        menu.add(new MenuItem("Es Jeruk", MenuItem.FoodType.BEVERAGE, 5000));
    }

    public static void initializePromo() {
        Promo promo = new Promo("Ramadan", Promo.PromoType.FREEBIES, "Pesan 2 makanan FREE 1 es teh manis!");

        // Set makanan sebagai eligible menu
        ArrayList<MenuItem> eligibleMenu = new ArrayList<>();
        for (MenuItem menuItem : menu) {
            if (menuItem.getFoodType().equals(MenuItem.FoodType.FOOD)) {
                eligibleMenu.add(menuItem);
            }
        }
        promo.setEligibleMenu(eligibleMenu);

        // Set es teh manis sebagai free menu item
        for (MenuItem menuItem : menu) {
            if (menuItem.getName().equals("Es Teh Manis")) {
                promo.setFreeMenuItem(new Order(menuItem, "M", 1, 0));
                break;
            }
        }

        // Set 2 sebagai requirement untuk mendapatkan promo
        promo.setRequiredMenuItemCount(2);

        // Set waktu berbuka saat Ramadan sebagai valid date dan time
        promo.setValidStartDate(LocalDate.of(2024, 3, 11));
        promo.setValidEndDate(LocalDate.of(2024, 4, 10));
        promo.setValidStartTime(LocalTime.of(17, 0));
        promo.setValidEndTime(LocalTime.of(21, 0));

        promos.add(promo);
    }
}
