package com.example.binarfud.repository;

import com.example.binarfud.model.entity.MenuItem;
import com.example.binarfud.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    boolean existsByRestaurantAndName(Restaurant restaurant, String name);
    boolean existsByName(String name);
    List<MenuItem> findByRestaurant(Restaurant restaurant);
}
