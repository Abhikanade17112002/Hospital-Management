package com.hospitalmanagement.dtos.departmentdtos;

public class AddHeadDoctorOfDepartmentRequestDTO {
    private String departmentId ;
    private String doctorId ;

    public AddHeadDoctorOfDepartmentRequestDTO(String departmentId, String doctorId) {
        this.departmentId = departmentId;
        this.doctorId = doctorId;
    }

    public AddHeadDoctorOfDepartmentRequestDTO() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "AddHeadDoctorOfDepartmentRequestDTO{" +
                "departmentId='" + departmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }
}
