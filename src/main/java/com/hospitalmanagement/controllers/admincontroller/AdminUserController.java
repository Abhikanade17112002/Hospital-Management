package com.hospitalmanagement.controllers.admincontroller;

import com.hospitalmanagement.dtos.userdtos.AssignRoleRequestDTO;
import com.hospitalmanagement.dtos.userdtos.AssignRoleResponseDTO;
import com.hospitalmanagement.dtos.userdtos.UserResponseDTO;
import com.hospitalmanagement.repositories.AppointmentRepository;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.repositories.InsuranceRepository;
import com.hospitalmanagement.repositories.UserRepository;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import com.hospitalmanagement.services.userservice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admins/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminUserController {
    private final ModelMapper modelMapper ;
    private final UserRepository userRepository ;
    private final PatientRepository patientRepository ;
    private final DoctorRepository doctorRepository ;
    private final AppointmentRepository appointmentRepository ;
    private final InsuranceRepository insuranceRepository ;
    private final UserService userService ;

    public AdminUserController(ModelMapper modelMapper, UserRepository userRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, InsuranceRepository insuranceRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.insuranceRepository = insuranceRepository;
        this.userService = userService;
    }

    @PostMapping("/assignrole")
    public ResponseEntity<AssignRoleResponseDTO> assignRole(@RequestBody AssignRoleRequestDTO assignRoleRequestDTO ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.assignRole(assignRoleRequestDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> getAllRegisteredUser(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllRegisteredUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getRegisteredUser(@PathVariable(name = "userId") String userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getRegisteredUser(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable(name = "userId") String userId ){
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }
    @PostMapping("/{userId}/togglestatus")
    public ResponseEntity<String> toggleUserStatus(@PathVariable(name = "userId") String userId ){
        return ResponseEntity.ok(userService.toggleUserStatus(userId));
    }
}
