package com.example.binarfud.service;

import com.example.binarfud.exception.RestaurantNotFoundException;
import com.example.binarfud.model.dto.restaurant.RestaurantCreateRequestDto;
import com.example.binarfud.model.dto.restaurant.RestaurantDto;
import com.example.binarfud.model.dto.restaurant.RestaurantUpdateRequestDto;
import com.example.binarfud.model.entity.*;
import com.example.binarfud.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired UserService userService;
    @Autowired ModelMapper modelMapper;

    @Override
    public RestaurantDto create(RestaurantCreateRequestDto restaurantCreateRequestDto) {
        Restaurant restaurant = modelMapper.map(restaurantCreateRequestDto, Restaurant.class);

        restaurant.setOwner(userService.getById(restaurantCreateRequestDto.getOwnerId()));
        restaurant.setOpen(true);

        restaurant = restaurantRepository.save(restaurant);
        log.info("Restaurant {} successfully added", restaurant.getName());

        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public Restaurant getById(UUID id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            return optionalRestaurant.get();
        }

        log.error("Restaurant not found: {}", (id));
        throw new RestaurantNotFoundException("Restaurant not found: " + id);
    }

    @Override
    public RestaurantDto getDtoById(UUID id) {
        return modelMapper.map(getById(id), RestaurantDto.class);
    }

    @Override
    public List<RestaurantDto> getList() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public List<RestaurantDto> getOpenList() {
        return restaurantRepository.findAll().stream()
                .filter(Restaurant::isOpen)
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public List<RestaurantDto> getListByUser(User user) {
        return restaurantRepository.findByOwner(user).stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public RestaurantDto update(UUID id, RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        Restaurant existingRestaurant = getById(id);

        existingRestaurant.setName(restaurantUpdateRequestDto.getName());
        existingRestaurant.setLocation(restaurantUpdateRequestDto.getLocation());
        existingRestaurant.setOpen(restaurantUpdateRequestDto.isOpen());
        existingRestaurant.setOwner(userService.getById(restaurantUpdateRequestDto.getOwnerId()));

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return modelMapper.map(updatedRestaurant, RestaurantDto.class);
    }

    @Override
    public void safeDeleteById(UUID id) {
        Restaurant restaurant = getById(id);
        restaurant.setDeleted(true);
        restaurantRepository.save(restaurant);
        log.info("Restaurant {} has been deleted", id);
    }
}
