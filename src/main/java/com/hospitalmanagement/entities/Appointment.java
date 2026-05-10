package com.hospitalmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalmanagement.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String appointmentId ;

    @Column(nullable = false)
    private LocalDateTime appointmentTime ;

    private String reason;

    @JoinColumn( name = "patient_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Patient patient ;


    @JoinColumn( name = "doctor_id" , nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Doctor doctor ;

    @Enumerated
    private AppointmentStatus appointmentStatus ;

    public Appointment() {
    }

    public Appointment(String appointmentId, LocalDateTime appointmentTime, String reason, Patient patient, Doctor doctor, AppointmentStatus appointmentStatus) {
        this.appointmentId = appointmentId;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", appointmentTime=" + appointmentTime +
                ", reason='" + reason + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", appointmentStatus=" + appointmentStatus +
                '}';
    }
}
