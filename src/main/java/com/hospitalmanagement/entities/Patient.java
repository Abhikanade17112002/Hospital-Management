package com.hospitalmanagement.entities;


import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
    @Column(nullable = false)
    private BloodGroup bloodGroup ;
    @UpdateTimestamp
    private LocalDateTime updatedAt ;
    @OneToOne
    @JoinColumn( name = "insurance_id")
    private Insurance insurance ;

    public Patient() {
    }

    public Patient(Insurance insurance,String patientId, String firstName, String lastName, String emailId, LocalDate dateOfBirth, Gender gender, LocalDateTime createdAt, BloodGroup bloodGroup, LocalDateTime updatedAt) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = createdAt;
        this.bloodGroup = bloodGroup;
        this.updatedAt = updatedAt;
        this.insurance = insurance ;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
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
                ", bloodGroup=" + bloodGroup +
                ", updatedAt=" + updatedAt +
                ", insurance=" + insurance +
                '}';
    }
}

