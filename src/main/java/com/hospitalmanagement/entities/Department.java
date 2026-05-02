package com.hospitalmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalmanagement.enums.DepartmentType;
import jakarta.persistence.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String departmentId ;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DepartmentType department ;

    @OneToOne
    @JoinColumn(name = "head_doctor_id")
    @JsonIgnore
    private Doctor headOfDepartment ;

    @ManyToMany
    @JoinTable(
            name = "department_doctors_mapping",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> departmentDoctors = new ArrayList<>() ;

    public Department() {

    }

    public Department(String departmentId, DepartmentType department, Doctor headOfDepartment, List<Doctor> departmentDoctors) {
        this.departmentId = departmentId;
        this.department = department;
        this.headOfDepartment = headOfDepartment;
        this.departmentDoctors = departmentDoctors;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }

    public Doctor getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Doctor headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public List<Doctor> getDepartmentDoctors() {
        return departmentDoctors;
    }

    public void setDepartmentDoctors(List<Doctor> departmentDoctors) {
        this.departmentDoctors = departmentDoctors;
    }


    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", department=" + department +
                ", headOfDepartment=" + headOfDepartment +
                ", departmentDoctors=" + departmentDoctors +
                '}';
    }
}
