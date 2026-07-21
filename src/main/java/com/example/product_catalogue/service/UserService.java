package com.example.product_catalogue.service;

import com.example.product_catalogue.dto.UserRequest;
import com.example.product_catalogue.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id,UserRequest request);

    void deleteUser(Long id);
}
