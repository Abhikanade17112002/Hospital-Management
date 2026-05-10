package com.hospitalmanagement.services.departmentservice.impl;

import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentDoctorsRequestDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentRequestDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentResponseDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddHeadDoctorOfDepartmentRequestDTO;
import com.hospitalmanagement.entities.Department;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.repositories.DepartmentRepository;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.services.departmentservice.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository ;
    private final ModelMapper modelMapper ;
    private final DoctorRepository doctorRepository ;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public AddDepartmentResponseDTO addDepartment(AddDepartmentRequestDTO addDepartmentRequestDTO) {
        Department department = new Department() ;
        department.setDepartment(addDepartmentRequestDTO.getDepartment());

        Department savedDepartment = departmentRepository.save(department) ;
        return modelMapper.map(savedDepartment, AddDepartmentResponseDTO.class);
    }

    @Override
    public List<AddDepartmentResponseDTO> getAllRegisteredDepartments() {
        List<Department> retrivedDepartments = departmentRepository.findAll() ;
        return retrivedDepartments.stream()
                .map((department -> modelMapper.map(department, AddDepartmentResponseDTO.class))).collect(Collectors.toList());

    }

    @Override
    public String deleteDepartmentById(String departmentId) {
        return "";
    }

    @Override
    public AddDepartmentResponseDTO assignDepartmentHeadDoctor(AddHeadDoctorOfDepartmentRequestDTO addHeadDoctorOfDepartmentRequestDTO) {
        Department department = departmentRepository.findById(addHeadDoctorOfDepartmentRequestDTO.getDepartmentId()).orElseThrow(()-> new EntityNotFoundException("Department With Department Id ==> " + addHeadDoctorOfDepartmentRequestDTO.getDepartmentId() + " Not Found"));
        Doctor doctor = doctorRepository.findById(addHeadDoctorOfDepartmentRequestDTO.getDoctorId()).orElseThrow(()-> new EntityNotFoundException("Doctor With Doctor Id ==> " + addHeadDoctorOfDepartmentRequestDTO.getDoctorId() + " Not Found"));
        department.setHeadOfDepartment(doctor);
        department.getDepartmentDoctors().add(doctor);
        return modelMapper.map(departmentRepository.save(department), AddDepartmentResponseDTO.class);
    }

    @Override
    public AddDepartmentResponseDTO addDepartmentDoctors(AddDepartmentDoctorsRequestDTO addDepartmentDoctorsRequestDTO) {
        List<Doctor> doctors = doctorRepository.findAllById(addDepartmentDoctorsRequestDTO.getDoctorIds());
        Department department = departmentRepository.findById(addDepartmentDoctorsRequestDTO.getDepartmentId()).orElseThrow();
        department.getDepartmentDoctors().addAll(doctors) ;
        for (Doctor doctor : doctors){
            doctor.getDepartments().add(department);
        }
        return modelMapper.map( departmentRepository.save(department) , AddDepartmentResponseDTO.class);
    }

    @Override
    public AddDepartmentResponseDTO removeDepartmentDoctors(AddDepartmentDoctorsRequestDTO addDepartmentDoctorsRequestDTO) {
        Department department = departmentRepository.findById(addDepartmentDoctorsRequestDTO.getDepartmentId()).orElseThrow();
        List<Doctor> doctors = doctorRepository.findAllById(addDepartmentDoctorsRequestDTO.getDoctorIds());
        List<Doctor> modifiedDoctorsList = department.getDepartmentDoctors().stream().
                filter((doctor)->  addDepartmentDoctorsRequestDTO.getDoctorIds().indexOf(doctor.getDoctorId()) == -1 ).collect(Collectors.toList());
        for(Doctor doctor : doctors){
            doctor.getDepartments().remove(department) ;
        }
        department.setDepartmentDoctors(modifiedDoctorsList);
        return modelMapper.map( departmentRepository.save(department),AddDepartmentResponseDTO.class);
    }
}
