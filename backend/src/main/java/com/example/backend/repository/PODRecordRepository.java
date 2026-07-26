package com.example.backend.repository;

import com.example.backend.entity.PODRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PODRecordRepository extends JpaRepository<PODRecord, Long> {
}