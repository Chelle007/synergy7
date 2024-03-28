package src.controller;

import java.util.ArrayList;

import src.model.entity.MenuItem;
import src.service.MenuItemService;
import src.service.MenuItemServiceImpl;
import src.view.MenuItemView;

public class MenuItemController {
    public void displayMenuItemList() {
        MenuItemService mis = new MenuItemServiceImpl();
        MenuItemView miv = new MenuItemView();

        ArrayList<MenuItem> menu = mis.getList();
        miv.displayMenuItemList(menu);
    }

    public void displayMenuItem(int choice) {
        MenuItemService mis = new MenuItemServiceImpl();
        MenuItemView miv = new MenuItemView();

        MenuItem menuItem = mis.get(choice-1);

        miv.displayMenuItemHeader();
        miv.displayMenuItem(choice, menuItem);
    }
}
