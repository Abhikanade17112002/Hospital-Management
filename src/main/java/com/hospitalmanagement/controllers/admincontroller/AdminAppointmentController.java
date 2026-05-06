package com.hospitalmanagement.controllers.admincontroller;


import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.repositories.AppointmentRepository;
import com.hospitalmanagement.services.appointmentservice.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/appointments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminAppointmentController {
    private final ModelMapper modelMapper ;
    private final AppointmentService appointmentService;

    public AdminAppointmentController(ModelMapper modelMapper, AppointmentService appointmentService) {
        this.modelMapper = modelMapper;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GetAppointmentResponseDTO>> getAllAppointments(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService.getAllAppointments());
    }
    @GetMapping("/{appointmentId}")
    public ResponseEntity<GetAppointmentResponseDTO> getAppointmentById(@PathVariable(name = "appointmentId") String appointmentId ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService.getAppointmentById(appointmentId));
    }
}
