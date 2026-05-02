package com.hospitalmanagement.dtos.userdtos;
import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UserSignUpRequestDTO {

    @NotBlank( message = "First Name Cannot Be Blank")
    private String firstName ;
    @NotBlank(message = "Last Name Cannot Be Blank")
    private String lastName ;
    @Email(message = "Email Id Should Be In Valid Format")
    private String emailId ;
    @NotBlank(message = "Password Cannot Be Blank")
    private String password ;
    private Gender gender ;
    private LocalDate dateOfBirth ;
    private BloodGroup bloodGroup ;


    public UserSignUpRequestDTO() {
    }

    public UserSignUpRequestDTO(String firstName, String lastName, String emailId, String password, Gender gender, LocalDate dateOfBirth, BloodGroup bloodGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
    }

    public  String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First Name Cannot Be Blank") String firstName) {
        this.firstName = firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last Name Cannot Be Blank") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Email Id Should Be In Valid Format") String getEmailId() {
        return emailId;
    }

    public void setEmailId(@Email(message = "Email Id Should Be In Valid Format") String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password Cannot Be Blank") String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(@NotBlank(message = "Gender Cannot Be Blank") Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date Of Birth Cannot Be Blank") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public  BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(@NotBlank(message = "Blood Group Cannot Be Blank") BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "UserSignUpRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodGroup=" + bloodGroup +
                '}';
    }
}
