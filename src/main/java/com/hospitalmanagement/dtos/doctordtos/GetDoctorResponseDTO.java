package com.hospitalmanagement.dtos.doctordtos;

import com.hospitalmanagement.entities.Appointment;
import com.hospitalmanagement.entities.Department;
import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import com.hospitalmanagement.enums.Specialization;

import java.util.ArrayList;
import java.util.List;

public class GetDoctorResponseDTO {
    private User user ;
    private String firstName ;
    private String lastName ;
    private Gender gender ;
    private BloodGroup bloodGroup ;
    private Specialization specialization ;
    private List<Appointment> appointments = new ArrayList<>() ;
    private List<Department> departments = new ArrayList<>() ;


    public GetDoctorResponseDTO() {
    }

    public GetDoctorResponseDTO( String firstName, String lastName, Gender gender, BloodGroup bloodGroup, Specialization specialization, List<Appointment> appointments, List<Department> departments, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.specialization = specialization;
        this.appointments = appointments;
        this.departments = departments;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GetDoctorResponseDTO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", bloodGroup=" + bloodGroup +
                ", specialization=" + specialization +
                ", appointments=" + appointments +
                ", departments=" + departments +
                ", user=" + user +
                '}';
    }
}
