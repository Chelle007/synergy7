package src.service;

import src.Data;
import src.exception.DuplicateMenuItemException;
import src.exception.MenuItemNotFoundException;
import src.model.entity.MenuItem;

import java.util.ArrayList;
import java.util.Set;

public class MenuItemServiceImpl implements MenuItemService {
    @Override
    public void create(MenuItem menuItem) {
        for (MenuItem m : Data.menu) {
            if (m.getName().equals(menuItem.getName())) {
                throw new DuplicateMenuItemException("Ditemukan menuItem dengan nama yang sama: " + menuItem.getName());
            }
        }

        Data.menu.add(menuItem);
    }

    @Override
    public MenuItem get(int choice) {
        if (choice < 0 || choice >= Data.menu.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        MenuItem menuItem = Data.menu.get(choice);
        if (menuItem == null) {
            throw new MenuItemNotFoundException("MenuItem tidak ditemukan: " + choice);
        }

        return menuItem;
    }

    @Override
    public ArrayList<MenuItem> getList() {
        return Data.menu;
    }

    @Override
    public Set<String> getAvailableSize(int choice) {
        MenuItem menuItem = get(choice);

        return menuItem.getAvailableSize().keySet();
    }

    @Override
    public void update(int choice, MenuItem menuItem) {
        get(choice);
        Data.menu.set(choice, menuItem);
    }

    @Override
    public void delete(int choice) {
        if (choice < 0 || choice >= Data.menu.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Data.menu.remove(choice);
    }

    @Override
    public void clearList() {
        Data.menu.clear();
    }
}
