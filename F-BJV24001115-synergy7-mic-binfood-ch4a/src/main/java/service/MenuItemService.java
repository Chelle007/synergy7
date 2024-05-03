package src.main.java.service;

import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;

public interface MenuItemService {
    void create(String name, String foodType, Integer priceS, int priceM, Integer priceL, Restaurant restaurant);
    MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice);
    boolean menuItemNameExistedInRestaurant(Restaurant restaurant, String name);
    String getAvailableFoodTypeChoice();
    boolean invalidFoodType(String foodType);
}
