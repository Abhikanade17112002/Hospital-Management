package com.hospitalmanagement.dtos.patientdtos;


import com.hospitalmanagement.entities.Appointment;
import com.hospitalmanagement.entities.Insurance;
import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetPatientResponseDTO {
    private String patientId ;
    private String firstName ;
    private String lastName ;
    private LocalDate dateOfBirth ;
    private Gender gender ;
    private LocalDateTime createdAt ;
    private BloodGroup bloodGroup ;
    private LocalDateTime updatedAt ;
    private Insurance insurance ;
    private List<Appointment> appointments = new ArrayList<>() ;
    private User user ;

    public GetPatientResponseDTO(String patientId, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, LocalDateTime createdAt, BloodGroup bloodGroup, LocalDateTime updatedAt, Insurance insurance, List<Appointment> appointments, User user) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = createdAt;
        this.bloodGroup = bloodGroup;
        this.updatedAt = updatedAt;
        this.insurance = insurance;
        this.appointments = appointments;
        this.user = user;
    }

    public GetPatientResponseDTO() {
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GetPatientResponseDTO{" +
                "patientId='" + patientId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", createdAt=" + createdAt +
                ", bloodGroup=" + bloodGroup +
                ", updatedAt=" + updatedAt +
                ", insurance=" + insurance +
                ", appointments=" + appointments +
                ", user=" + user +
                '}';
    }
}
