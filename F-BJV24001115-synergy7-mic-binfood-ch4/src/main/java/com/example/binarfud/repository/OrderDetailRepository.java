package com.example.binarfud.repository;

import com.example.binarfud.model.entity.MenuItem;
import com.example.binarfud.model.entity.Order;
import com.example.binarfud.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByOrder(Order order);
    Optional<OrderDetail> findByOrderAndMenuItemAndSize(Order order, MenuItem menuItem, String size);
}
