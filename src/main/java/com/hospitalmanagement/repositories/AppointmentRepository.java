package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}