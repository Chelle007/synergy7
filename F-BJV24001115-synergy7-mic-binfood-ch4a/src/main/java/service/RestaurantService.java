package src.main.java.service;

import src.main.java.model.entity.Restaurant;
import src.main.java.model.entity.User;

import java.util.List;

public interface RestaurantService {
    // CREATE
    void create(Restaurant restaurant);

    // READ
    Restaurant getByChoice(int choice);
    List<Restaurant> getList();
    // DELETE
    void clearList();
}
