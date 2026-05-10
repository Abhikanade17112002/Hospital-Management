package com.hospitalmanagement.dtos.doctordtos;

import com.hospitalmanagement.entities.Appointment;
import com.hospitalmanagement.entities.Department;
import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import com.hospitalmanagement.enums.Specialization;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AddDoctorResponseDTO {
    private String doctorId;

    private String firstName ;

    private String lastName ;

    private Gender gender ;

    private BloodGroup bloodGroup ;

    private Specialization specialization ;

    private String email ;

    private List<Appointment> appointments = new ArrayList<>() ;

    private List<Department> departments = new ArrayList<>() ;

    public AddDoctorResponseDTO() {
    }

    public AddDoctorResponseDTO(String doctorId, String firstName, String lastName, Gender gender, BloodGroup bloodGroup, Specialization specialization, String email, List<Appointment> appointments, List<Department> departments) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.specialization = specialization;
        this.email = email;
        this.appointments = appointments;
        this.departments = departments;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "AddDoctorResponseDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", bloodGroup=" + bloodGroup +
                ", specialization=" + specialization +
                ", email='" + email + '\'' +
                ", appointments=" + appointments +
                ", departments=" + departments +
                '}';
    }
}
