package com.example.backend.service;

import com.example.backend.dto.PODRecordDTO;
import com.example.backend.entity.PODRecord;

import java.util.List;

public interface PODRecordService {

    PODRecordDTO create(PODRecord podRecord);

    List<PODRecordDTO> getAll();

    PODRecordDTO getById(Long id);

    PODRecordDTO update(Long id, PODRecord podRecord);

    void delete(Long id);
}