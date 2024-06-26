package src.main.java.controller;

import src.main.java.model.entity.MenuItem;
import src.main.java.service.MenuItemService;
import src.main.java.service.MenuItemServiceImpl;
import src.main.java.view.MenuItemView;

import java.util.List;

public class MenuItemController {
    public void displayMenuItemList() {
        MenuItemService mis = new MenuItemServiceImpl();
        MenuItemView miv = new MenuItemView();

        List<MenuItem> menu = mis.getList();
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
