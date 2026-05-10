package com.hospitalmanagement.dtos.doctordtos;
import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import com.hospitalmanagement.enums.Specialization;
import java.time.LocalDate;

public class UpdateDoctorProfileRequestDTO {
    private String doctorId;
    private String firstName ;
    private String lastName ;
    private Gender gender ;
    private BloodGroup bloodGroup ;
    private Specialization specialization ;
    private LocalDate dateOfBirth ;

    public UpdateDoctorProfileRequestDTO(String doctorId, String firstName, String lastName, Gender gender, BloodGroup bloodGroup, Specialization specialization, LocalDate dateOfBirth) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.specialization = specialization;
        this.dateOfBirth = dateOfBirth;
    }

    public UpdateDoctorProfileRequestDTO() {
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UpdateDoctorProfileRequestDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", bloodGroup=" + bloodGroup +
                ", specialization=" + specialization +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
