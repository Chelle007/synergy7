package src.view;

import src.model.entity.Restaurant;

import java.util.List;

public class RestaurantView {
    public void displayRestaurantList(List<Restaurant> restaurants) {
        for (int i = 0; i<restaurants.size(); i++) {
            Restaurant restaurant = restaurants.get(i);
            displayRestaurant(i+1, restaurant);
        }
    }

    public void displayRestaurant(int num, Restaurant restaurant) {
        System.out.println(num + ". " + restaurant.getName());
    }
}
