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
    private Restaurant restaurant;

    public enum Role {
        CUSTOMER,
        SELLER
    }

    public List<Order> getOrderList() {
        return Data.ORDERS.stream()
                .filter(o -> o.getUser().getId() == this.id)
                .toList();
    }
}
