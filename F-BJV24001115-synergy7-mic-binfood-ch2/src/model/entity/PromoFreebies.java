package src.main.java.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoFreebies extends Promo {
    private Order freeMenuItem;
    private int requiredMenuItemCount;

    // CONSTRUCTOR
    public PromoFreebies(String name, String description) {
        super(name, description);
    }
}
