package com.example.binarfud.controller;

import com.example.binarfud.model.dto.user.UserCreateRequestDto;
import com.example.binarfud.model.dto.user.UserDto;
import com.example.binarfud.model.dto.user.UserUpdateRequestDto;
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
@RequestMapping("users")
public class UserController2 {
    @Autowired UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("user", userService.create(userCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<UserDto> userList = userService.getList();
        data.put("users", userList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable("user_id") UUID userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        UserDto userDto = userService.getDtoById(userId);
        data.put("user", userDto);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> getUserByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        UserDto userDto = userService.getByUsernameAndPassword(username, password);
        data.put("user", userDto);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("user_id") UUID userId, UserUpdateRequestDto userUpdateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        UserDto updatedUser = userService.update(userId, userUpdateRequestDto);
        data.put("user", updatedUser);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("user_id") UUID userId) {
        userService.safeDeleteById(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
