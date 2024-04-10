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
    private boolean completed;
    private Customer customer;
    private Restaurant restaurant;

    public List<OrderDetail> getOrderDetailList() {
        return Data.ORDER_DETAILS.stream()
                .filter(item -> item.getOrder().getId() == this.id)
                .toList();
    }
}
