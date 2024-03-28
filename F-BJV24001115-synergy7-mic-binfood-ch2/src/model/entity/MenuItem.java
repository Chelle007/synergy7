package src.model.entity;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {
    private String name;
    private FoodType foodType;
    private int defaultPrice;
    private final HashMap<String, Integer> availableSize;

    public enum FoodType {
        FOOD,
        BEVERAGE
    }

    // CONSTRUCTOR
    public MenuItem(String name, FoodType foodType, int defaultPrice) {
        this.name = name;
        this.foodType = foodType;
        this.defaultPrice = defaultPrice;
        availableSize = new HashMap<>();
        availableSize.put("M", defaultPrice);
    }

    public MenuItem(String name, FoodType foodType, int defaultPrice, int difference) {
        this.name = name;
        this.foodType = foodType;
        this.defaultPrice = defaultPrice;
        availableSize = new HashMap<>();
        availableSize.put("S", defaultPrice - difference);
        availableSize.put("M", defaultPrice);
        availableSize.put("L", defaultPrice + difference);
    }

    public MenuItem(String name, FoodType foodType, int priceS, int priceM, int priceL) {
        this.name = name;
        this.foodType = foodType;
        defaultPrice = priceM;
        availableSize = new HashMap<>();
        availableSize.put("S", priceS);
        availableSize.put("M", priceM);
        availableSize.put("L", priceL);
    }

    // METHODS
    public Integer getSizePrice(String size) {
        return availableSize.get(size);
    }
}

