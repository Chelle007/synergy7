package com.example.binarfud.service;

import com.example.binarfud.model.dto.restaurant.RestaurantCreateRequestDto;
import com.example.binarfud.model.dto.restaurant.RestaurantDto;
import com.example.binarfud.model.dto.restaurant.RestaurantUpdateRequestDto;
import com.example.binarfud.model.entity.*;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    // CREATE
    RestaurantDto create(RestaurantCreateRequestDto restaurantCreateRequestDto);

    // READ
    Restaurant getById(UUID id);
    RestaurantDto getDtoById(UUID id);
    List<RestaurantDto> getList();
    List<RestaurantDto> getOpenList();
    List<RestaurantDto> getByUser(User user);

    // UPDATE
    RestaurantDto update(UUID id, RestaurantUpdateRequestDto restaurantUpdateRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
