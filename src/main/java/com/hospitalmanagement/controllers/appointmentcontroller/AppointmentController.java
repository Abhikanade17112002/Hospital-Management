package com.hospitalmanagement.controllers.appointmentcontroller;


import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentRequestDTO;
import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentResponseDTO;
import com.hospitalmanagement.services.appointmentservice.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService ;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/addappointment")
    public ResponseEntity<AddAppointmentResponseDTO> addNewAppointment(@RequestBody AddAppointmentRequestDTO appointment ){
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                appointmentService.addNewAppointment(appointment)
        );
    }
}
