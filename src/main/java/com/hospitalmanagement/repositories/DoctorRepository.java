package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
}