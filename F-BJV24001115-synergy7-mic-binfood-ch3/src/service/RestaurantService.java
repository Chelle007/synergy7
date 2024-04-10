package src.service;

import src.model.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    // CREATE
    void create(Restaurant restaurant);

    // READ
    Restaurant getById(int choice);
    List<Restaurant> getList();

    // DELETE
    void deleteById(int choice);
    void clearList();
}
