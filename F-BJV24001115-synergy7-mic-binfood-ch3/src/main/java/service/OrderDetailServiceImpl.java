package src.main.java.service;

import src.main.java.Data;
import src.main.java.model.entity.Order;
import src.main.java.model.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public void create(OrderDetail orderDetail) {
        Optional<OrderDetail> existingOrderDetail = Data.ORDER_DETAILS.stream()
                .filter(od -> od.getMenuItem().equals(orderDetail.getMenuItem()) && od.getSize().equals(orderDetail.getSize()))
                .findFirst();

        if (existingOrderDetail.isPresent()) {
            existingOrderDetail.get().addQty(orderDetail.getQty());
        } else {
            Data.ORDER_DETAILS.add(orderDetail);
        }
    }

    @Override
    public OrderDetail getByChoice(Order order, int choice) {
        List<OrderDetail> orderDetails = order.getOrderDetailList();
        choice--;

        if (choice < 0 || choice >= orderDetails.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return orderDetails.get(choice);
    }

    @Override
    public void update(Order order, int choice, String size, int qty) {
        OrderDetail orderDetail = getByChoice(order, choice);

        if (orderDetail.getSize().equals(size)) {
            orderDetail.setQty(qty);
        } else {
            OrderDetail updatedOrderDetail = new OrderDetail(size, qty, orderDetail.getMenuItem(), order);
            Data.ORDER_DETAILS.remove(orderDetail);
            create(updatedOrderDetail);
        }
    }

    @Override
    public void deleteByChoice(Order order, int choice) {
        OrderDetail orderDetail = getByChoice(order, choice);

        Data.ORDER_DETAILS.remove(orderDetail);
    }

    @Override
    public void clearList(Order order) {
        Data.ORDER_DETAILS.removeIf(od -> od.getOrder() == order);
    }
}
