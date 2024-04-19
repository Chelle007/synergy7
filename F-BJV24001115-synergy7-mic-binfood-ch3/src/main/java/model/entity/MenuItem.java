package src.main.java.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.main.java.Data;

@Getter
@Setter
@AllArgsConstructor
public class MenuItem {
    private int id;
    private String name;
    private FoodType foodType;
    private Integer priceS;
    private Integer priceM;
    private Integer priceL;
    private Restaurant restaurant;

    public enum FoodType {
        FOOD,
        BEVERAGE
    }

    public MenuItem(String name, FoodType foodType, int priceM, Restaurant restaurant) {
        this.id = Data.MENU_ITEMS.size();
        this.name = name;
        this.foodType = foodType;
        this.priceM = priceM;
        this.restaurant = restaurant;
    }

    public MenuItem(String name, FoodType foodType, int priceS, int priceM, int priceL, Restaurant restaurant) {
        this.id = Data.MENU_ITEMS.size();
        this.name = name;
        this.foodType = foodType;
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
        this.restaurant = restaurant;
    }

    public Integer getSizePrice(String size) {
        return switch (size) {
            case "S" -> priceS;
            case "M" -> priceM;
            case "L" -> priceL;
            default -> null;
        };
    }
}

