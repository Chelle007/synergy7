package src.main.java.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.main.java.Data;

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

    public OrderDetail(String size, int qty, MenuItem menuItem, Order order) {
        this.id = Data.ORDER_DETAILS.size();
        this.size = size;
        this.qty = qty;
        this.price = menuItem.getSizePrice(size);
        this.menuItem = menuItem;
        this.order = order;
    }

    public void addQty(int qty) {
        this.qty += qty;
    }
}
