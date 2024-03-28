package src.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private final MenuItem menuItem;
    private final String size;
    private int qty;
    private int price;

    // CONSTRUCTOR
    public Order(MenuItem menuItem, String size, int qty) {
        this.menuItem = menuItem;
        this.size = size;
        this.qty = qty;
        this.price = menuItem.getSizePrice(size);
    }

    public Order(MenuItem menuItem, String size, int qty, int price) {
        this.menuItem = menuItem;
        this.size = size;
        this.qty = qty;
        this.price = price;
    }

    // METHODS
    public void addQty(int qty) {
        this.qty += qty;
    }
}
