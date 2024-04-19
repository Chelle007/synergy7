package src.main.java.service;

import src.main.java.model.entity.MenuItem;

import java.util.List;
import java.util.Set;

public interface MenuItemService {
    MenuItem get(int choice);
    List<MenuItem> getList();
    Set<String> getAvailableSize(int choice);
}
