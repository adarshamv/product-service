package com.example.product_catalogue.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "User name is required")
    private String name;

    @Email
    private String email;
}
