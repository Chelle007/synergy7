package src.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoFreebies extends Promo {
    private OrderDetail freeMenuItem;
    private int requiredMenuItemCount;

    // CONSTRUCTOR
    public PromoFreebies(int id, Restaurant restaurant, String name, String description) {
        super(id, restaurant, name, description);
    }
}
