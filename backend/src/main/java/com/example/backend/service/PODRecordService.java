package com.example.backend.service;

import com.example.backend.entity.PODRecord;

import java.util.List;

public interface PODRecordService {

    PODRecord createPODRecord(PODRecord podRecord);

    List<PODRecord> getAllPODRecords();

    PODRecord getPODRecordById(Long id);

    PODRecord updatePODRecord(Long id, PODRecord podRecord);

    void deletePODRecord(Long id);
}