package com.example.backend.service;

import com.example.backend.dto.ReturnRecordDTO;
import java.util.List;

public interface ReturnRecordService {
    ReturnRecordDTO create(ReturnRecordDTO dto);
    List<ReturnRecordDTO> getAll();
    ReturnRecordDTO getById(Long id);
    ReturnRecordDTO getByDeliveryOrderId(Long orderId);
    ReturnRecordDTO update(Long id, ReturnRecordDTO dto);
    void delete(Long id);
}
