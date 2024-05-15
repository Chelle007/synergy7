//package com.example.binarfud.controller;
//
//import com.example.binarfud.model.entity.*;
//import com.example.binarfud.service.*;
//import com.example.binarfud.view.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//public class RestaurantController {
//    @Autowired RestaurantService restaurantService;
//    @Autowired RestaurantView restaurantView;
//
//    public void displayOpenRestaurantList() {
//        List<Restaurant> restaurants = restaurantService.getOpenList();
//        restaurantView.displayRestaurantList(restaurants);
//    }
//
//    public void displayOpenRestaurantList(User user) {
//        List<Restaurant> restaurants = restaurantService.getByUser(user);
//        restaurantView.displayRestaurantList(restaurants);
//    }
//
//    public Restaurant createRestaurant(String name, String location, User user) {
//        Restaurant restaurant = Restaurant.builder()
//                .name(name)
//                .location(location)
//                .owner(user)
//                .open(true)
//                .build();
//
//        return restaurantService.create(restaurant);
//    }
//}
