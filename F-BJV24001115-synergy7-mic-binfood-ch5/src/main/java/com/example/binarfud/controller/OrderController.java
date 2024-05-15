package com.example.binarfud.controller;

import com.example.binarfud.model.dto.order.OrderCreateRequestDto;
import com.example.binarfud.model.dto.order.OrderDto;
import com.example.binarfud.model.dto.order.OrderCompleteRequestDto;
import com.example.binarfud.service.OrderService;
import com.example.binarfud.service.RestaurantService;
import com.example.binarfud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired OrderService orderService;
    @Autowired UserService userService;
    @Autowired RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("order", orderService.create(orderCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<OrderDto> orderList = orderService.getList();
        data.put("orders", orderList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/history/user/{user_id}")
    public ResponseEntity<Map<String, Object>> getCompletedOrdersByUser(@PathVariable("user_id") UUID userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<OrderDto> orderList = orderService.getCompletedListByUser(userService.getById(userId));
        data.put("orders", orderList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/history/restaurant/{restaurant_id}")
    public ResponseEntity<Map<String, Object>> getCompletedOrdersByRestaurant(@PathVariable("restaurant_id") UUID restaurantId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<OrderDto> orderList = orderService.getCompletedListByRestaurant(restaurantService.getById(restaurantId));
        data.put("orders", orderList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable("order_id") UUID orderId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        OrderDto orderDto = orderService.getDtoById(orderId);
        data.put("order", orderDto);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/complete_order/{order_id}")
    public ResponseEntity<Map<String, Object>> completeOrder(@PathVariable("order_id") UUID orderId, OrderCompleteRequestDto orderCompleteRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        OrderDto updatedOrder = orderService.completeOrder(orderId, orderCompleteRequestDto);
        data.put("order", updatedOrder);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable("order_id") UUID orderId) {
        orderService.safeDeleteById(orderId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
