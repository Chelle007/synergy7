package src.service;

import src.Data;
import src.exception.MenuItemNotFoundException;
import src.model.entity.MenuItem;
import src.model.entity.Restaurant;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {
    @Override
    public MenuItem get(int choice) {
        if (choice < 0 || choice >= Data.MENU_ITEMS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        MenuItem menuItem = Data.MENU_ITEMS.get(choice);
        if (menuItem == null) {
            throw new MenuItemNotFoundException("MenuItem tidak ditemukan: " + choice);
        }

        return menuItem;
    }

    @Override
    public MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice) {
        List<MenuItem> menuItems = restaurant.getMenuItemList();
        choice--;

        if (choice < 0 || choice >= menuItems.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        MenuItem menuItem = menuItems.get(choice);
        if (menuItem == null) {
            throw new MenuItemNotFoundException("MenuItem tidak ditemukan: " + choice);
        }

        return menuItem;
    }

    @Override
    public List<MenuItem> getList() {
        return Data.MENU_ITEMS;
    }
}
