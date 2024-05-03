package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;

import java.util.List;

public interface RestaurantService {
    // CREATE
    Restaurant create(Restaurant restaurant);

    // READ
    Restaurant getByChoice(int choice);
    Restaurant getByOpenChoice(int choice);
    List<Restaurant> getList();
    List<Restaurant> getOpenList();
    List<Restaurant> getByUser(User user);
    Restaurant getByUserAndChoice(User user, int choice);
    boolean restaurantNameExisted(String name);

    // UPDATE
    void changeStatus(Restaurant restaurant);
    void updateName(Restaurant restaurant, String name);
    void updateLocation(Restaurant restaurant, String location);

    // DELETE
    void safeDelete(Restaurant restaurant);
}
