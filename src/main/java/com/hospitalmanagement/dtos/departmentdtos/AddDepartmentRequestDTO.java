package com.hospitalmanagement.dtos.departmentdtos;

import com.hospitalmanagement.enums.DepartmentType;

public class AddDepartmentRequestDTO {
    private DepartmentType department ;

    public AddDepartmentRequestDTO(DepartmentType department) {
        this.department = department;
    }

    public AddDepartmentRequestDTO() {
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "AddDepartmentRequestDTO{" +
                "department=" + department +
                '}';
    }
}
