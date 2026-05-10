package com.hospitalmanagement.controllers.doctorcontroller;

import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.dtos.doctordtos.GetDoctorResponseDTO;
import com.hospitalmanagement.dtos.doctordtos.UpdateDoctorProfileRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.services.doctorservices.DoctorService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService ;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping("/{doctorId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') and (#doctorId == authentication.principal.userId)")
    public ResponseEntity<GetDoctorResponseDTO> getDoctorById(@PathVariable(name = "doctorId") String doctorId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(doctorService.getRegisteredDoctorById(doctorId));
    }

    @PutMapping("/{doctorId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') and (#doctorId == authentication.principal.userId)")
    public ResponseEntity<GetDoctorResponseDTO> updateDoctorProfile(@PathVariable(name = "doctorId") String doctorId, @RequestBody UpdateDoctorProfileRequestDTO updateDoctorProfileRequestDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(doctorService.updateDoctorProfile(doctorId,updateDoctorProfileRequestDTO));
    }

    @GetMapping("/{doctorId}/appointments")
    @PreAuthorize("hasRole('ROLE_DOCTOR') and #doctorId == authentication.principal.userId")
    public ResponseEntity<List<GetAppointmentResponseDTO>> getDoctorAppointments(@PathVariable(name = "doctorId") String doctorId ) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        doctorService.getDoctorAppointments(doctorId)
                );

    }

    @DeleteMapping("/{doctorId}/deleteappointment/{appointmentId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') and #doctorId == authentication.principal.userId")
    public ResponseEntity<GetDoctorResponseDTO> deleteDoctorAppointmentById(@PathVariable(name = "doctorId") String doctorId, @PathVariable(name = "appointmentId") String appointmentId ) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .body(
                        doctorService.deleteDoctorAppointmentById(doctorId,appointmentId)
                );

    }


}
