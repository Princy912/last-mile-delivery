package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, User user) {

      User existingUser = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found"));

      existingUser.setName(user.getName());
      existingUser.setEmail(user.getEmail());
      existingUser.setPhone(user.getPhone());
      existingUser.setPassword(user.getPassword());
      existingUser.setRole(user.getRole());
      existingUser.setIsActive(user.getIsActive());

      return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {

      User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found"));

      userRepository.delete(user);
    }
}