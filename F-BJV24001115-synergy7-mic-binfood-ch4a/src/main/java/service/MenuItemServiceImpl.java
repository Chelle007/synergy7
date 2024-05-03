package src.main.java.service;

import src.main.java.Data;
import src.main.java.exception.MenuItemNameExistedException;
import src.main.java.exception.RestaurantNotFoundException;
import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {

    @Override
    public void create(String name, String foodTypeString, Integer priceS, int priceM, Integer priceL, Restaurant restaurant) {
        MenuItem.FoodType foodType;

        if (foodTypeString.equals("BEVERAGE")) foodType = MenuItem.FoodType.BEVERAGE;
        else foodType = MenuItem.FoodType.FOOD;

        MenuItem menuItem = new MenuItem(name, foodType, priceS, priceM, priceL, restaurant);

        if (Data.RESTAURANTS.stream().anyMatch(r -> r == restaurant)) {
            throw new RestaurantNotFoundException("Restaurant tidak ditemukan: " + restaurant.getName());
        } else if (restaurant.getMenuItemList().stream().anyMatch(mi -> mi.getName().equals(name))) {
            throw new MenuItemNameExistedException("Nama menu item telah dipakai: " + name);
        } else {
            menuItem.setId(Data.MENU_ITEMS.size());
            Data.MENU_ITEMS.add(menuItem);
        }
    }

    @Override
    public MenuItem getByRestaurantAndChoice(Restaurant restaurant, int choice) {
        List<MenuItem> menuItems = restaurant.getMenuItemList();
        choice--;

        if (choice < 0 || choice >= menuItems.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return menuItems.get(choice);
    }

    @Override
    public boolean menuItemNameExistedInRestaurant(Restaurant restaurant, String name) {
        return restaurant.getMenuItemList().stream().anyMatch(menuItem -> menuItem.getName().equals(name));
    }

    @Override
    public String getAvailableFoodTypeChoice() {
        return "FOOD/BEVERAGE";
    }

    @Override
    public boolean invalidFoodType(String foodType) {
        return !(foodType.equalsIgnoreCase("FOOD") || foodType.equalsIgnoreCase("BEVERAGE"));
    }
}
