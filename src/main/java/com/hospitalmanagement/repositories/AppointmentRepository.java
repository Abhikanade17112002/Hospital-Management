package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.Appointment;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findAllByDoctor(Doctor doctor);

    List<Appointment> findAllByPatient(Patient patient);
}