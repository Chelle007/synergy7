package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant create(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        log.info("{} berhasil ditambahkan", restaurant.getName());
        return restaurant;
    }

    @Override
    public Restaurant getByChoice(int choice) {
        List<Restaurant> restaurants = getList();
        choice--;

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurants.get(choice).getId());
        int finalChoice = choice;
        return optionalRestaurant.orElseThrow(() -> new IndexOutOfBoundsException("Pilihan invalid: " + (finalChoice)));
    }

    @Override
    public Restaurant getByOpenChoice(int choice) {
        List<Restaurant> restaurants = getOpenList();
        choice--;

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurants.get(choice).getId());
        int finalChoice = choice;
        return optionalRestaurant.orElseThrow(() -> new IndexOutOfBoundsException("Pilihan invalid: " + (finalChoice)));
    }

    @Override
    public List<Restaurant> getList() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getOpenList() {
        return restaurantRepository.findByOpen(true);
    }

    @Override
    public List<Restaurant> getByUser(User user) {
        return restaurantRepository.findByOwner(user);
    }

    @Override
    public Restaurant getByUserAndChoice(User user, int choice) {
        List<Restaurant> restaurants = getByUser(user);
        choice--;

        if (choice < 0 || choice >= restaurants.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return restaurants.get(choice);
    }

    @Override
    public boolean restaurantNameExisted(String name) {
        return restaurantRepository.existsByName(name);
    }

    @Override
    public void changeStatus(Restaurant restaurant) {
        boolean isOpen = restaurant.isOpen();
        restaurant.setOpen(!isOpen);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateName(Restaurant restaurant, String name) {
        restaurant.setName(name);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateLocation(Restaurant restaurant, String location) {
        restaurant.setLocation(location);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void safeDelete(Restaurant restaurant) {
        restaurant.setDeleted(true);
        restaurantRepository.save(restaurant);
        log.info("Restaurant {} telah dihapus", restaurant.getId());
    }
}
