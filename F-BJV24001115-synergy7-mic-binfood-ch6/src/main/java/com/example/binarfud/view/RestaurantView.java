//package com.example.binarfud.view;
//
//import com.example.binarfud.model.entity.*;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class RestaurantView {
//    public void displayRestaurantList(List<Restaurant> restaurants) {
//        AtomicInteger index = new AtomicInteger(1);
//        restaurants.forEach(restaurant -> displayRestaurant(index.getAndIncrement(), restaurant));
//    }
//
//    public void displayRestaurant(int num, Restaurant restaurant) {
//        System.out.println(num + ". " + restaurant.getName());
//    }
//}
