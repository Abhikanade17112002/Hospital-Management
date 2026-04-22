package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, String> {
}