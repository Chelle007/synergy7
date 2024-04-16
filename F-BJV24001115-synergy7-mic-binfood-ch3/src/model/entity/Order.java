package src.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.Data;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private int id;
    private LocalDateTime orderTime;
    private String destinationAddress;
    private String notes;
    private boolean completed;
    private User user;
    private Restaurant restaurant;

    public Order(Restaurant restaurant, User user) {
        this.id = Data.ORDERS.size();
        this.restaurant = restaurant;
        this.user = user;
        this.completed = false;
    }

    public List<OrderDetail> getOrderDetailList() {
        return Data.ORDER_DETAILS.stream()
                .filter(item -> item.getOrder().getId() == this.id)
                .toList();
    }
}
