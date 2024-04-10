package src.service;

import src.model.entity.MenuItem;

import java.util.List;
import java.util.Set;

public interface MenuItemService {
    MenuItem get(int choice);
    List<MenuItem> getList();
}
