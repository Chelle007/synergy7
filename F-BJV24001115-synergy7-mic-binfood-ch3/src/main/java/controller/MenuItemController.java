package src.main.java.controller;

import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;
import src.main.java.service.MenuItemService;
import src.main.java.service.MenuItemServiceImpl;
import src.main.java.view.MenuItemView;

import java.util.List;

public class MenuItemController {
    public void displayMenuItemList(Restaurant restaurant) {
        MenuItemView miv = new MenuItemView();

        List<MenuItem> menu = restaurant.getMenuItemList();
        miv.displayMenuItemList(menu);
    }

    public void displayMenuItem(Restaurant restaurant, int choice) {
        MenuItemService mis = new MenuItemServiceImpl();
        MenuItemView miv = new MenuItemView();

        MenuItem menuItem = mis.getByRestaurantAndChoice(restaurant, choice);

        miv.displayMenuItemHeader();
        miv.displayMenuItem(choice, menuItem);
    }
}
