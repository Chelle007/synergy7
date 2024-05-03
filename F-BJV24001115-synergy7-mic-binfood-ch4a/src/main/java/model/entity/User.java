package src.main.java.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.main.java.Data;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Role role;

    public enum Role {
        CUSTOMER,
        SELLER
    }

    public List<Order> getOrderList() {
        return Data.ORDERS.stream()
                .filter(o -> o.getUser() == this)
                .toList();
    }

    public List<Restaurant> getRestaurantList() {
        return Data.RESTAURANTS.stream()
                .filter(r -> r.getOwner() == this)
                .toList();
    }
}
