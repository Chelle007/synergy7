package src.service;

import src.Data;
import src.exception.DuplicateMenuItemException;
import src.exception.MenuItemNotFoundException;
import src.model.entity.MenuItem;

import java.util.List;
import java.util.Set;

public class MenuItemServiceImpl implements MenuItemService {
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
    public List<MenuItem> getList() {
        return Data.menu;
    }

    @Override
    public Set<String> getAvailableSize(int choice) {
        MenuItem menuItem = get(choice);

        return menuItem.getAvailableSize().keySet();
    }
}
