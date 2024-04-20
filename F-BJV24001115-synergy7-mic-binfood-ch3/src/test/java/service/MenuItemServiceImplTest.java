package src.test.java.service;

import src.main.java.Data;
import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;
import src.main.java.service.MenuItemService;
import src.main.java.service.MenuItemServiceImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemServiceImplTest {
    MenuItemService mis;
    Restaurant restaurant;

    @BeforeEach
    void initObj() {
        mis = new MenuItemServiceImpl();
        restaurant = Data.restaurant;
    }

    @BeforeAll
    static void initData() {
        Data.initializeRestaurants();
        Data.initializeMenu();
    }

    @Test
    void getByRestaurantAndChoice() {
        Exception e = assertThrows(
                IndexOutOfBoundsException.class, () -> mis.getByRestaurantAndChoice(restaurant, 0));

        assertEquals("Pilihan invalid: -1", e.getMessage());
    }

    @Test
    void getByRestaurantAndChoice2() {
        int previousSize = Data.MENU_ITEMS.size();
        MenuItem menuItem = new MenuItem("Pizza", MenuItem.FoodType.FOOD, 7000, restaurant);
        Data.MENU_ITEMS.add(menuItem);
        MenuItem menuItem1 = mis.getByRestaurantAndChoice(restaurant, previousSize+1);

        assertEquals(menuItem, menuItem1);
    }
}
