package src.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetail {
    private int id;
    private String size;
    private int qty;
    private int price;
    private MenuItem menuItem;
    private Order order;

    public void addQty(int qty) {
        this.qty += qty;
    }
}
