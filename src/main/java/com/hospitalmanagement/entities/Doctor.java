package com.hospitalmanagement.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hospitalmanagement.enums.BloodGroup;
import com.hospitalmanagement.enums.Gender;
import com.hospitalmanagement.enums.Specialization;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "doctorId"
)
public class Doctor {

    @Id
    private String doctorId;
    private String firstName ;
    private String lastName ;
    @Enumerated(value = EnumType.STRING)
    private Gender gender ;
    @Enumerated(value = EnumType.STRING)
    private BloodGroup bloodGroup ;
    @Enumerated(value = EnumType.STRING)
    private Specialization specialization ;
    private LocalDate dateOfBirth ;
    @OneToMany( mappedBy = "doctor",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>() ;
    @ManyToMany(mappedBy = "departmentDoctors" , fetch = FetchType.EAGER)
    private List<Department> departments = new ArrayList<>() ;
    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_Id")
    private User user ;

    public Doctor() {
    }

    public Doctor(String doctorId, String firstName, String lastName, Gender gender, BloodGroup bloodGroup, Specialization specialization, LocalDate dateOfBirth, List<Appointment> appointments, List<Department> departments, User user) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.specialization = specialization;
        this.dateOfBirth = dateOfBirth;
        this.appointments = appointments;
        this.departments = departments;
        this.user = user;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", bloodGroup=" + bloodGroup +
                ", specialization=" + specialization +
                ", dateOfBirth=" + dateOfBirth +
                ", appointments=" + appointments +
                ", departments=" + departments +
                ", user=" + user +
                '}';
    }
}
