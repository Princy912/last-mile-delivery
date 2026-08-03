package com.example.backend.service;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(User user);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, User user);

    void deleteUser(Long id);

    UserDTO getUserByEmail(String email);
}