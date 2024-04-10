package src.controller;

import src.model.entity.Restaurant;
import src.service.RestaurantService;
import src.service.RestaurantServiceImpl;
import src.view.RestaurantView;

import java.util.List;

public class RestaurantController {
    public void displayRestaurantList() {
        RestaurantService rs = new RestaurantServiceImpl();
        RestaurantView rv = new RestaurantView();

        List<Restaurant> restaurants = rs.getList();
        rv.displayRestaurantList(restaurants);
    }
}
