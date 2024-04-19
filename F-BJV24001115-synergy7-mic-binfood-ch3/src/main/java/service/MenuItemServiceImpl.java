package src.main.java.service;

import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {

    @Override
    public MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice) {
        List<MenuItem> menuItems = restaurant.getMenuItemList();
        choice--;

        if (choice < 0 || choice >= menuItems.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return menuItems.get(choice);
    }

}
