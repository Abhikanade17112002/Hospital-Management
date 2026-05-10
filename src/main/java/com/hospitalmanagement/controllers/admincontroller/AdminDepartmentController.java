package com.hospitalmanagement.controllers.admincontroller;


import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentDoctorsRequestDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentRequestDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddDepartmentResponseDTO;
import com.hospitalmanagement.dtos.departmentdtos.AddHeadDoctorOfDepartmentRequestDTO;
import com.hospitalmanagement.services.departmentservice.DepartmentService;
import com.hospitalmanagement.services.doctorservices.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/departments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminDepartmentController {
    private final ModelMapper modelMapper ;
    private final DepartmentService departmentService ;
    private final DoctorService doctorService ;

    public AdminDepartmentController(ModelMapper modelMapper, DepartmentService departmentService, DoctorService doctorService) {
        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AddDepartmentResponseDTO>> getAllRegisteredDepartments(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(departmentService.getAllRegisteredDepartments());
    }

    @PostMapping("/adddepartment")
    public ResponseEntity<AddDepartmentResponseDTO> addDepartment(@RequestBody AddDepartmentRequestDTO addDepartmentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.addDepartment(addDepartmentRequestDTO));
    }
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(name = "departmentId") String departmentId ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(departmentService.deleteDepartmentById(departmentId));
    }
    @PostMapping("/assigndepartmentheaddoctor")
    public ResponseEntity<AddDepartmentResponseDTO> assignDepartmentHeadDoctor(@RequestBody AddHeadDoctorOfDepartmentRequestDTO addHeadDoctorOfDepartmentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.assignDepartmentHeadDoctor(addHeadDoctorOfDepartmentRequestDTO));
    }
    @PostMapping("/adddepartmentdoctors")
    public ResponseEntity<AddDepartmentResponseDTO> addDepartmentDoctors(@RequestBody AddDepartmentDoctorsRequestDTO addDepartmentDoctorsRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.addDepartmentDoctors(addDepartmentDoctorsRequestDTO));
    }
    @DeleteMapping("/removedepartmentdoctors")
    public ResponseEntity<AddDepartmentResponseDTO> removeDepartmentDoctors(@RequestBody AddDepartmentDoctorsRequestDTO addDepartmentDoctorsRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.removeDepartmentDoctors(addDepartmentDoctorsRequestDTO));
    }

}
