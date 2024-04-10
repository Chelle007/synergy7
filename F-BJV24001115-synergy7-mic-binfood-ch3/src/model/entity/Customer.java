package src.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.Data;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private int id;
    private String username;
    private String email;
    private String password;

    public List<Order> getOrderList() {
        return Data.ORDERS.stream()
                .filter(o -> o.getCustomer().getId() == this.id)
                .toList();
    }
}
