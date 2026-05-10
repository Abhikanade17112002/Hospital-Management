package com.hospitalmanagement.controllers.admincontroller;

import com.hospitalmanagement.dtos.doctordtos.GetDoctorResponseDTO;
import com.hospitalmanagement.services.doctorservices.DoctorService;
import com.hospitalmanagement.services.userservice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/doctors")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminDoctorController {
    private final ModelMapper modelMapper ;
    private final UserService userService ;
    private final DoctorService doctorService ;

    public AdminDoctorController(ModelMapper modelMapper, UserService userService, DoctorService doctorService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GetDoctorResponseDTO>> getRegisteredDoctors(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorService.getRegisteredDoctors());
    }
    @GetMapping("/{doctorId}")
    public ResponseEntity<GetDoctorResponseDTO> getRegisteredDoctorById(@PathVariable(name = "doctorId") String doctorId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorService.getRegisteredDoctorById(doctorId));
    }
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable(name = "doctorId") String doctorId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorService.deleteDoctorById(doctorId));
    }
    @PostMapping("/onboard/{userId}")
    public ResponseEntity<GetDoctorResponseDTO> onBoardDoctorByUserId(@PathVariable(name = "userId") String userId ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.onBoardDoctorByUserId(userId)) ;
    }
}
