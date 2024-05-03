package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface MenuItemService {
    // CREATE
    MenuItem create(MenuItem menuItem);

    // READ
    MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice);
    List<MenuItem> getByRestaurant(Restaurant restaurant);
    boolean menuItemNameExistedInRestaurant(Restaurant restaurant, String name);
    String getAvailableFoodTypeChoice();
    boolean invalidFoodType(String foodType);
    ArrayList<String> getAvailableSize(MenuItem menuItem);
    MenuItem.Type convertStringToType(String type);

    // UPDATE
    void updateName(MenuItem menuItem, String name);
    void updateType(MenuItem menuItem, MenuItem.Type type);
    void updatePriceS(MenuItem menuItem, int price);
    void updatePriceM(MenuItem menuItem, int price);
    void updatePriceL(MenuItem menuItem, int price);

    // DELETE
    void safeDelete(MenuItem menuItem);
}
