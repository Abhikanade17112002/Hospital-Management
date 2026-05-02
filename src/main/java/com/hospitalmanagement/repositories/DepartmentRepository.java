package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}