package com.example.backend.service.serviceImpl;

import com.example.backend.dto.DeliveryAddressDTO;
import com.example.backend.entity.DeliveryAddress;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.DeliveryAddressMapper;
import com.example.backend.repository.DeliveryAddressRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.DeliveryAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserRepository userRepository;

    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository, UserRepository userRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DeliveryAddressDTO create(DeliveryAddressDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        DeliveryAddress entity = DeliveryAddressMapper.toEntity(dto);
        entity.setUser(user);
        DeliveryAddress saved = deliveryAddressRepository.save(entity);
        return DeliveryAddressMapper.toDTO(saved);
    }

    @Override
    public List<DeliveryAddressDTO> getAll() {
        return deliveryAddressRepository.findAll().stream()
                .map(DeliveryAddressMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryAddressDTO getById(Long id) {
        DeliveryAddress entity = deliveryAddressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Address not found"));
        return DeliveryAddressMapper.toDTO(entity);
    }

    @Override
    public List<DeliveryAddressDTO> getByUserId(Long userId) {
        return deliveryAddressRepository.findByUserId(userId).stream()
                .map(DeliveryAddressMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryAddressDTO update(Long id, DeliveryAddressDTO dto) {
        DeliveryAddress existing = deliveryAddressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Address not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setUser(user);
        existing.setAddressLine1(dto.getAddressLine1());
        existing.setAddressLine2(dto.getAddressLine2());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setPostalCode(dto.getPostalCode());
        existing.setCountry(dto.getCountry());
        existing.setIsDefault(dto.getIsDefault() != null ? dto.getIsDefault() : false);

        DeliveryAddress updated = deliveryAddressRepository.save(existing);
        return DeliveryAddressMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        DeliveryAddress existing = deliveryAddressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Address not found"));
        deliveryAddressRepository.delete(existing);
    }
}
