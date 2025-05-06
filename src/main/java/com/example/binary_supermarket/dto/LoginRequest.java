package com.example.binary_supermarket.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
