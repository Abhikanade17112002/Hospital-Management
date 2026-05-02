package com.hospitalmanagement.controllers.doctorcontroller;

import com.hospitalmanagement.dtos.doctordtos.AddDoctorRequestDTO;
import com.hospitalmanagement.dtos.doctordtos.AddDoctorResponseDTO;
import com.hospitalmanagement.services.doctorservices.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService ;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("adddoctor")
    public ResponseEntity<AddDoctorResponseDTO> addNewDoctor(@RequestBody AddDoctorRequestDTO doctor){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                doctorService.addNewDoctor(doctor)
        ) ;
    }
}
