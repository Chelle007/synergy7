package src.main.java.view;

import src.main.java.model.entity.Restaurant;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RestaurantView {
    public void displayRestaurantList(List<Restaurant> restaurants) {
        AtomicInteger index = new AtomicInteger(1);
        restaurants.forEach(restaurant -> displayRestaurant(index.getAndIncrement(), restaurant));
    }

    public void displayRestaurant(int num, Restaurant restaurant) {
        System.out.println(num + ". " + restaurant.getName());
    }
}
