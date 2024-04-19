package src.test.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Data;
import src.main.java.model.entity.Restaurant;
import src.main.java.service.RestaurantService;
import src.main.java.service.RestaurantServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantServiceImplTest {
    RestaurantService rs;
    Restaurant restaurant;

    @BeforeEach
    void init() {
        rs = new RestaurantServiceImpl();
        restaurant = Data.restaurant;
        Data.RESTAURANTS.add(restaurant);
    }

    @Test
    void create() {
        int previousSize = Data.RESTAURANTS.size();
        rs.create(restaurant);

        assertEquals(previousSize+1, Data.RESTAURANTS.size());
    }

    @Test
    void getByChoice() {
        Restaurant restaurant2 = rs.getByChoice(1);

        assertEquals(restaurant, restaurant2);
    }

    @Test
    void getByChoice2() {
        Exception e = assertThrows(
                IndexOutOfBoundsException.class, () -> rs.getByChoice(0));

        assertEquals("Pilihan invalid: -1", e.getMessage());
    }

    @Test
    void getList() {
        List<Restaurant> restaurants = rs.getList();

        assertEquals(Data.RESTAURANTS, restaurants);
    }

    @Test
    void clearList() {
        rs.clearList();

        assertEquals(0, Data.RESTAURANTS.size());
    }
}
