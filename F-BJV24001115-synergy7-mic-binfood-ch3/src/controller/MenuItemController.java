package src.controller;

import src.model.entity.MenuItem;
import src.model.entity.Restaurant;
import src.service.MenuItemService;
import src.service.MenuItemServiceImpl;
import src.view.MenuItemView;

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
