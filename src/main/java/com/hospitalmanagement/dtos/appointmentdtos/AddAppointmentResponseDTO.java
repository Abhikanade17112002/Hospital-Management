package com.hospitalmanagement.dtos.appointmentdtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.entities.Patient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AddAppointmentResponseDTO {
    private String appointmentId ;
    private LocalDateTime appointmentTime ;
    private String reason;
    private Patient patient ;
    private Doctor doctor ;

    public AddAppointmentResponseDTO() {
    }

    public AddAppointmentResponseDTO(String appointmentId, LocalDateTime appointmentTime, String reason, Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "AddAppointmentResponseDTO{" +
                "appointmentId='" + appointmentId + '\'' +
                ", appointmentTime=" + appointmentTime +
                ", reason='" + reason + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
