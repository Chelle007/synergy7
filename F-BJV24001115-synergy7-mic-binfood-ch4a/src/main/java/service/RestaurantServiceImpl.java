package src.main.java.service;

import src.main.java.Data;
import src.main.java.exception.RestaurantNotFoundException;
import src.main.java.model.entity.Restaurant;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public void create(Restaurant restaurant) {
        restaurant.setId(Data.RESTAURANTS.size());
        Data.RESTAURANTS.add(restaurant);
    }

    @Override
    public Restaurant getByChoice(int choice) {
        choice--;
        if (choice < 0 || choice >= Data.RESTAURANTS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return Data.RESTAURANTS.get(choice);
    }

    @Override
    public List<Restaurant> getList() {
        return Data.RESTAURANTS;
    }

    @Override
    public void clearList() {
        Data.RESTAURANTS.clear();
    }
}
