package src.service;

import src.model.entity.MenuItem;
import src.model.entity.Restaurant;

import java.util.List;
import java.util.Set;

public interface MenuItemService {
    MenuItem get(int choice);
    MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice);
    List<MenuItem> getList();
}
