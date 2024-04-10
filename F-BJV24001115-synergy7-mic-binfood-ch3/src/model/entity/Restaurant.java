package src.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.Data;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private int id;
    private String name;
    private String location;
    private boolean open;

    public List<MenuItem> getMenuItemList() {
        return Data.MENU_ITEMS.stream()
                .filter(i -> i.getRestaurant().getId() == this.id)
                .toList();
    }

    public List<Promo> getPromoList() {
        return Data.PROMOS.stream()
                .filter(p -> p.getRestaurant().getId() == this.id)
                .toList();
    }

    public List<Order> getOrderList() {
        return Data.ORDERS.stream()
                .filter(o -> o.getRestaurant().getId() == this.id)
                .toList();
    }
}
