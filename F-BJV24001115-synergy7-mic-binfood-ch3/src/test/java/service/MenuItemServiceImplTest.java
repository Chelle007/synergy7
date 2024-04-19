package src.test.java.service;

import src.main.java.Data;
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
}
