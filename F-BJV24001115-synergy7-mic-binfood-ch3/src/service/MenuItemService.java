package src.service;

import src.model.entity.MenuItem;
import src.model.entity.Restaurant;

import java.util.List;

public interface MenuItemService {
    MenuItem get(int choice);
    MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice);
    List<MenuItem> getList();
}
