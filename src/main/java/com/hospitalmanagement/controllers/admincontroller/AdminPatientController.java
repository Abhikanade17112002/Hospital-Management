package com.hospitalmanagement.controllers.admincontroller;

import com.hospitalmanagement.dtos.doctordtos.GetDoctorResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.services.patientservice.PatientService;
import com.hospitalmanagement.services.userservice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/patients")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminPatientController {
    private final ModelMapper modelMapper ;
    private final PatientService patientService ;
    private final UserService userService ;

    public AdminPatientController(ModelMapper modelMapper, PatientService patientService, UserService userService) {
        this.modelMapper = modelMapper;
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GetPatientResponseDTO>> getAllRegisteredPatients(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(patientService.getRegisteredPatients());
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<GetPatientResponseDTO> getRegisteredPatientById(@PathVariable(name = "patientId") String patientId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(patientService.getRegisteredPatientById(patientId));
    }
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deleteRegisteredPatientById(@PathVariable(name = "patientId") String patientId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(patientService.deleteRegisteredPatientById(patientId));
    }
    @PostMapping("/onboard/{userId}")
    public ResponseEntity<GetPatientResponseDTO> onBoardPatientByUserId(@PathVariable(name = "userId") String userId ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.onBoardPatientByUserId(userId)) ;
    }
}
