package src.service;

import src.Data;
import src.exception.OrderNotFoundException;
import src.model.entity.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public void create(Order order) {
        order.setId(Data.ORDERS.size());
        Data.ORDERS.add(order);
    }

    @Override
    public Order getById(int id) {
        Optional<Order> order = Data.ORDERS.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order tidak ditemukan: " + id);
        }

        return order.get();
    }

    @Override
    public List<Order> getList() {
        return Data.ORDERS;
    }

    @Override
    public void deleteById(int id) {
        Order order = getById(id);
        Data.ORDERS.remove(order);
    }

    @Override
    public void clearList() {
        Data.ORDERS.clear();
    }
}
