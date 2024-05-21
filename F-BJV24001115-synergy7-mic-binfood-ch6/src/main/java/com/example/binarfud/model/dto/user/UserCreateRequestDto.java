package com.example.binarfud.model.dto.user;

import com.example.binarfud.model.entity.User;
import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String username;
    private String email;
    private String password;
    private User.Role role;
}
