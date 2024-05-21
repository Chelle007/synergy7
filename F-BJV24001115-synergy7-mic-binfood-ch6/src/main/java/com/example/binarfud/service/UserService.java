package com.example.binarfud.service;

import com.example.binarfud.model.dto.user.UserCreateRequestDto;
import com.example.binarfud.model.dto.user.UserDto;
import com.example.binarfud.model.dto.user.UserUpdateRequestDto;
import com.example.binarfud.model.entity.*;

import java.util.List;
import java.util.UUID;

public interface UserService {
    // CREATE
    UserDto create(UserCreateRequestDto userCreateRequestDto);

    // READ
    User getById(UUID id);
    UserDto getDtoById(UUID id);
    UserDto getByUsernameAndPassword(String username, String password);
    List<UserDto> getList();

    // UPDATE
    UserDto update(UUID id, UserUpdateRequestDto userUpdateRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
