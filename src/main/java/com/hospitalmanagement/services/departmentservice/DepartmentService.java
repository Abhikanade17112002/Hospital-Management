package com.hospitalmanagement.services.departmentservice;

import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentDoctorsRequestDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentRequestDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentResponseDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddHeadDoctorOfDepartmentRequestDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface DepartmentService {
    public AddDepartmentResponseDTO addDepartment(AddDepartmentRequestDTO addDepartmentRequestDTO);
    public List<AddDepartmentResponseDTO> getAllRegisteredDepartments();
    String deleteDepartmentById(String departmentId);
    AddDepartmentResponseDTO assignDepartmentHeadDoctor(AddHeadDoctorOfDepartmentRequestDTO addHeadDoctorOfDepartmentRequestDTO);
    AddDepartmentResponseDTO addDepartmentDoctors(AddDepartmentDoctorsRequestDTO addDepartmentDoctorsRequestDTO);
    AddDepartmentResponseDTO removeDepartmentDoctors(AddDepartmentDoctorsRequestDTO addDepartmentDoctorsRequestDTO);
}
