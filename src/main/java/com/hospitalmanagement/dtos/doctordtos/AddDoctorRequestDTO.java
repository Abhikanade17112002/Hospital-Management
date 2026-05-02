package com.hospitalmanagement.dtos.doctordtos;

import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import com.hospitalmanagement.enums.Specialization;

public class AddDoctorRequestDTO {
    private String firstName ;

    private String lastName ;

    private Gender gender ;

    private BloodGroup bloodGroup ;

    private Specialization specialization ;

    private String email ;

    public AddDoctorRequestDTO() {
    }

    public AddDoctorRequestDTO(String firstName, String lastName, Gender gender, BloodGroup bloodGroup, Specialization specialization, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.specialization = specialization;
        this.email = email;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddDoctorRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", bloodGroup=" + bloodGroup +
                ", specialization=" + specialization +
                ", email='" + email + '\'' +
                '}';
    }
}
