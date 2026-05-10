package com.hospitalmanagement.dtos.departmentdtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.enums.DepartmentType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AddDepartmentResponseDTO {
    private String departmentId ;
    private DepartmentType department ;
    private Doctor headOfDepartment ;
    private List<Doctor> departmentDoctors = new ArrayList<>() ;

    public AddDepartmentResponseDTO() {
    }

    public AddDepartmentResponseDTO(String departmentId, DepartmentType department, Doctor headOfDepartment, List<Doctor> departmentDoctors) {
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
        return "AddDepartmentResponseDTO{" +
                "departmentId='" + departmentId + '\'' +
                ", department=" + department +
                ", headOfDepartment=" + headOfDepartment +
                ", departmentDoctors=" + departmentDoctors +
                '}';
    }
}
