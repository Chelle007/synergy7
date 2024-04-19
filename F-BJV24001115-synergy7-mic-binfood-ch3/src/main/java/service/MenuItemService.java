package src.main.java.service;

import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;

public interface MenuItemService {
    MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice);
}
