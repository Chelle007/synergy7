package com.example.binarfud.service;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findByOrderAndMenuItemAndSize(orderDetail.getOrder(), orderDetail.getMenuItem(), orderDetail.getSize());

        if (existingOrderDetail.isPresent()) {
            OrderDetail updatedOrderDetail = existingOrderDetail.get();
            updatedOrderDetail.addQty(orderDetail.getQty());
            updatedOrderDetail.setPrice(updatedOrderDetail.getQty()*orderDetail.getMenuItem().getSizePrice(orderDetail.getSize()));
            orderDetailRepository.save(updatedOrderDetail);
            return updatedOrderDetail;
        } else {
            orderDetail.setPrice(orderDetail.getQty()*orderDetail.getMenuItem().getSizePrice(orderDetail.getSize()));
            orderDetailRepository.save(orderDetail);
        }

        return orderDetail;
    }

    @Override
    public OrderDetail getByChoice(Order order, int choice) {
        List<OrderDetail> orderDetails = getByOrder(order);
        choice--;

        if (choice < 0 || choice >= orderDetails.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        return orderDetails.get(choice);
    }

    @Override
    public List<OrderDetail> getByOrder(Order order) {
        return orderDetailRepository.findByOrder(order);
    }

    @Override
    public void update(Order order, int choice, String size, int qty) {
        OrderDetail oldOrderDetail = getByChoice(order, choice);

        if (oldOrderDetail.getSize().equals(size)) {
            oldOrderDetail.setQty(qty);
            oldOrderDetail.setPrice(qty*oldOrderDetail.getMenuItem().getSizePrice(size));
            orderDetailRepository.save(oldOrderDetail);
        } else {
            OrderDetail newOrderDetail = OrderDetail.builder()
                    .menuItem(oldOrderDetail.getMenuItem())
                    .size(size)
                    .qty(qty)
                    .order(oldOrderDetail.getOrder())
                    .build();
            orderDetailRepository.delete(oldOrderDetail);
            create(newOrderDetail);
        }
    }

    @Override
    public void safeDeleteByOrderAndChoice(Order order, int choice) {
        OrderDetail orderDetail = getByChoice(order, choice);
        orderDetail.setDeleted(true);
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void safeDeleteAllOrderDetailsByOrder(Order order) {
        List<OrderDetail> orderDetails = getByOrder(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setDeleted(true);
            orderDetailRepository.save(orderDetail);
        }
    }
}
