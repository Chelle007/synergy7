package src.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    public Integer getSizePrice(String size) {
        return switch (size) {
            case "S" -> priceS;
            case "M" -> priceM;
            case "L" -> priceL;
            default -> null;
        };
    }
}

