package src.service;

import src.model.entity.MenuItem;

import java.util.List;
import java.util.Set;

public interface MenuItemService {
    // CREATE
    void create(MenuItem menuItem);

    // READ
    MenuItem get(int choice);
    List<MenuItem> getList();

    Set<String> getAvailableSize(int choice);

    // UPDATE
    void update(int choice, MenuItem menuItem);

    // DELETE
    void delete(int choice);
    void clearList();
}
