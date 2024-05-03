package com.example.binarfud.service;

import com.example.binarfud.exception.MenuItemNameExistedException;
import com.example.binarfud.exception.RestaurantNotFoundException;
import com.example.binarfud.model.entity.*;
import com.example.binarfud.model.entity.MenuItem;
import com.example.binarfud.repository.MenuItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public MenuItem create(MenuItem menuItem) {
        if (menuItemRepository.existsByName(menuItem.getName())) {
            throw new MenuItemNameExistedException("Nama menu item telah dipakai: " + menuItem.getName());
        }

        menuItemRepository.save(menuItem);
        log.info("{} berhasil ditambahkan", menuItem.getName());
        return menuItem;
    }

    @Override
    public MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice) {
        List<MenuItem> menuItems = getByRestaurant(restaurant);
        choice--;

        if (choice < 0 || choice >= menuItems.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return menuItems.get(choice);
    }

    @Override
    public List<MenuItem> getByRestaurant(Restaurant restaurant) {
        return menuItemRepository.findByRestaurant(restaurant);
    }

    @Override
    public boolean menuItemNameExistedInRestaurant(Restaurant restaurant, String name) {
        return menuItemRepository.existsByRestaurantAndName(restaurant, name);
    }

    @Override
    public String getAvailableFoodTypeChoice() {
        return Stream.of(MenuItem.Type.values())
                .map(Enum::name)
                .collect(Collectors.joining("/"));
    }

    @Override
    public boolean invalidFoodType(String foodType) {
        try {
            MenuItem.Type.valueOf(foodType.toUpperCase());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    @Override
    public ArrayList<String> getAvailableSize(MenuItem menuItem) {
        ArrayList<String> availableSize = new ArrayList<>();

        availableSize.add("M");
        if (menuItem.getPriceS()!=null) availableSize.add("S");
        if (menuItem.getPriceL()!=null) availableSize.add("L");

        return availableSize;
    }

    @Override
    public MenuItem.Type convertStringToType(String type) {
        for (MenuItem.Type enumValue : MenuItem.Type.values()) {
            if (enumValue.name().equalsIgnoreCase(type)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Tipe menu item invalid: " + type);
    }

    @Override
    public void updateName(MenuItem menuItem, String name) {
        menuItem.setName(name);
        menuItemRepository.save(menuItem);
    }

    @Override
    public void updateType(MenuItem menuItem, MenuItem.Type type) {
        menuItem.setType(type);
        menuItemRepository.save(menuItem);
    }

    @Override
    public void updatePriceS(MenuItem menuItem, int price) {
        Integer finalPrice = price;
        if (price == 0) finalPrice = null;
        menuItem.setPriceS(finalPrice);
        menuItemRepository.save(menuItem);
    }

    @Override
    public void updatePriceM(MenuItem menuItem, int price) {
        menuItem.setPriceM(price);
        menuItemRepository.save(menuItem);
    }

    @Override
    public void updatePriceL(MenuItem menuItem, int price) {
        Integer finalPrice = price;
        if (price == 0) finalPrice = null;
        menuItem.setPriceL(finalPrice);
        menuItemRepository.save(menuItem);
    }

    @Override
    public void safeDelete(MenuItem menuItem) {
        menuItem.setDeleted(true);
        menuItemRepository.save(menuItem);
        log.info("Menu item {} telah dihapus", menuItem.getId());
    }
}
