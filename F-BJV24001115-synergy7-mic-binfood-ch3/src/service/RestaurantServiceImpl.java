package src.service;

import src.Data;
import src.exception.CustomerNotFoundException;
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
        Optional<Restaurant> merchant = Data.RESTAURANTS.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        if (merchant.isEmpty()) {
            throw new CustomerNotFoundException("Merchant tidak ditemukan: " + id);
        }

        return merchant.get();
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
