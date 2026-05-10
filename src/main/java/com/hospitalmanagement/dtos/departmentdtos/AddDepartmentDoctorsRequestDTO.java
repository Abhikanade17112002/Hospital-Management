package com.hospitalmanagement.dtos.departmentdtos;

import java.util.ArrayList;
import java.util.List;

public class AddDepartmentDoctorsRequestDTO {
    private String departmentId ;
    private List<String> doctorIds = new ArrayList<>() ;

    public AddDepartmentDoctorsRequestDTO() {
    }

    public AddDepartmentDoctorsRequestDTO(String departmentId, List<String> doctorIds) {
        this.departmentId = departmentId;
        this.doctorIds = doctorIds;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List<String> getDoctorIds() {
        return doctorIds;
    }

    public void setDoctorIds(List<String> doctorIds) {
        this.doctorIds = doctorIds;
    }

    @Override
    public String toString() {
        return "AddDepartmentDoctorsRequestDTO{" +
                "departmentId='" + departmentId + '\'' +
                ", doctorIds=" + doctorIds +
                '}';
    }
}
