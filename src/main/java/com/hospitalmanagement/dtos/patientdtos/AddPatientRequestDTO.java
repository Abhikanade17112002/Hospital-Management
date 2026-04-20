package com.hospitalmanagement.dtos.patientdtos;

import com.hospitalmanagement.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddPatientRequestDTO {


    @NotBlank(message = "First Name Should Be Not Blank")
    private String firstName ;

    @NotBlank(message = "Last Name Should Be Not Blank")
    private String lastName ;

    @Email( message =  "Email Id Should Be In A Proper Format")
    private String emailId ;

    private LocalDate dateOfBirth ;

    private Gender gender ;

    public AddPatientRequestDTO() {
    }

    public AddPatientRequestDTO(String firstName, String lastName, String emailId, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "AddPatientRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }
}
