package com.example.binarfud.controller;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.service.*;
import com.example.binarfud.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MenuItemController {
    @Autowired MenuItemView menuItemView;
    @Autowired MenuItemService menuItemService;

    public void createMenuItem(String name, String typeString, Integer priceS, int priceM, Integer priceL, Restaurant restaurant) {
        MenuItem.Type type = menuItemService.convertStringToType(typeString);

        MenuItem menuItem = MenuItem.builder()
                .name(name)
                .type(type)
                .priceS(priceS)
                .priceM(priceM)
                .priceL(priceL)
                .restaurant(restaurant)
                .build();

        menuItemService.create(menuItem);
    }

    public void displayMenuItemList(Restaurant restaurant) {
        List<MenuItem> menu = menuItemService.getByRestaurant(restaurant);
        menuItemView.displayMenuItemList(menu);
    }

    public void displayMenuItem(Restaurant restaurant, int choice) {
        MenuItem menuItem = menuItemService.getByRestaurantAndChoice(restaurant, choice);

        menuItemView.displayMenuItemHeader();
        menuItemView.displayMenuItem(choice, menuItem);
    }
}
