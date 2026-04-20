package com.hospitalmanagement.entities;


import com.hospitalmanagement.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "patient")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_patient_email", columnNames = {"emailId"}
                )
                ,
                @UniqueConstraint(
                        name = "unique_patient_entry", columnNames = {"firstName", "lastName", "dateOfBirth"}
                )
        }
        ,
        indexes = {

                @Index(name = "idex_patient_email",
                        columnList = "emailId"
                )

        }
)
public class Patient {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String patientId ;


    private String firstName ;

    private String lastName ;

    @Column(unique = true)
    private String emailId ;
    private LocalDate dateOfBirth ;
    @Enumerated( value = EnumType.STRING)
    private Gender gender ;
    @CreationTimestamp
    @Column( updatable = false )
    private LocalDateTime createdAt ;
    @UpdateTimestamp
    private LocalDateTime updatedAt ;

    public Patient() {
    }

    public Patient(String patientId, String firstName, String lastName, String emailId, LocalDate dateOfBirth, Gender gender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

