package src.service;

import src.Data;
import src.exception.CustomerNotFoundException;
import src.exception.RestaurantNotFoundException;
import src.model.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public void create(Restaurant restaurant) {
        restaurant.setId(Data.RESTAURANTS.size());
        Data.RESTAURANTS.add(restaurant);
    }

    @Override
    public Restaurant getById(int id) {
        Optional<Restaurant> restaurant = Data.RESTAURANTS.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        if (restaurant.isEmpty()) {
            throw new CustomerNotFoundException("Merchant tidak ditemukan: " + id);
        }

        return restaurant.get();
    }

    @Override
    public Restaurant getByChoice(int choice) {
        choice--;
        if (choice < 0 || choice >= Data.RESTAURANTS.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Restaurant restaurant = Data.RESTAURANTS.get(choice);
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant tidak ditemukan: " + choice);
        }

        return restaurant;
    }

    @Override
    public List<Restaurant> getList() {
        return Data.RESTAURANTS;
    }

    @Override
    public void deleteById(int id) {
        Restaurant restaurant = getById(id);
        Data.RESTAURANTS.remove(restaurant);
    }

    @Override
    public void clearList() {
        Data.RESTAURANTS.clear();
    }
}
