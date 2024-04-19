package src.main.java.controller;

import src.main.java.model.entity.Restaurant;
import src.main.java.service.RestaurantService;
import src.main.java.service.RestaurantServiceImpl;
import src.main.java.view.RestaurantView;

import java.util.List;

public class RestaurantController {
    public void displayRestaurantList() {
        RestaurantService rs = new RestaurantServiceImpl();
        RestaurantView rv = new RestaurantView();

        List<Restaurant> restaurants = rs.getList();
        rv.displayRestaurantList(restaurants);
    }
}
