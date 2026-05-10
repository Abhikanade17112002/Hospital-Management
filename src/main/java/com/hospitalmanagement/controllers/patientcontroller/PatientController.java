package com.hospitalmanagement.controllers.patientcontroller;


import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentRequestDTO;
import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.AddPatientRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.UpdatePatientProfileRequestDTO;
import com.hospitalmanagement.services.patientservice.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @PostMapping("/register")
    public ResponseEntity<GetPatientResponseDTO> registerPatient(@Valid @RequestBody AddPatientRequestDTO patient) {

        System.out.println(patient);
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .body(
                        patientService.registerPatient(patient)
                );

    }

    @GetMapping("/")
    public ResponseEntity<List<GetPatientResponseDTO>> getRegisteredPatients() {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getRegisteredPatients()
                );

    }




    @GetMapping("/findByFirstNameContainingOrderByFirstName/{firstName}")
    public ResponseEntity<List<GetPatientResponseDTO>> getPatientsByFirstNameContaining(@PathVariable(name = "firstName") String firstName ){
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getPatientsByFirstNameContainingAndOrderByFirstName(firstName)
                );

    }

    @GetMapping("/datesbetween")
    public ResponseEntity<List<GetPatientResponseDTO>> getPatientsDateOfBirthBetween(@RequestParam LocalDate startDate , @RequestParam LocalDate endDate  ){
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getPatientsByDateOfBirthBetween(startDate,endDate)
                );


    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasRole('ROLE_PATIENT') and #patientId == authentication.principal.userId")
    public ResponseEntity<GetPatientResponseDTO> getRegisteredPatientById(@PathVariable(name = "patientId") String patientId ) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getRegisteredPatientById(patientId)
                );

    }
    @PutMapping("/{patientId}")
    @PreAuthorize("hasRole('ROLE_PATIENT') and #patientId == authentication.principal.userId")
    public ResponseEntity<GetPatientResponseDTO> updatePatientProfile(@PathVariable(name = "patientId") String patientId, @RequestBody UpdatePatientProfileRequestDTO updatePatientProfileRequestDTO) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.updatePatientProfile(patientId,updatePatientProfileRequestDTO)
                );

    }


    @GetMapping("/bornafter")
    public ResponseEntity<List<GetPatientResponseDTO>> getRegisteredPatientBornAfter(@RequestParam LocalDate bornAfter ) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getRegisteredPatientBornAfter(bornAfter)
                );

    }
    @GetMapping("/bornafternative")
    public ResponseEntity<List<GetPatientResponseDTO>> getRegisteredPatientBornAfterNative(@RequestParam LocalDate bornAfter ) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getRegisteredPatientBornAfterNative(bornAfter)
                );

    }

    @GetMapping("/genderpatientcount")
    public ResponseEntity<List<Object[]>> getGenderPatientCount( ) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getGenderPatientCount()
                );

    }

    @GetMapping("/getpagablepatients")
    public ResponseEntity<List<GetPatientResponseDTO>> getPagablePatients(@RequestParam("pageno") int pageNo , @RequestParam("noofrecords") int noOfRecords , @RequestParam("sortBy") String sortBy) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getPagablePatients(pageNo,noOfRecords,sortBy)
                );

    }

    @PostMapping("/{patientId}/requestappointment")
    @PreAuthorize("hasRole('ROLE_PATIENT') and #patientId == authentication.principal.userId")
    public ResponseEntity<GetAppointmentResponseDTO> requestDoctorAppointment(@PathVariable(name = "patientId") String patientId , @RequestBody AddAppointmentRequestDTO addAppointmentRequestDTO) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .body(
                        patientService.requestDoctorAppointment(addAppointmentRequestDTO)
                );

    }

    @GetMapping("/{patientId}/appointments")
    @PreAuthorize("hasRole('ROLE_PATIENT') and #patientId == authentication.principal.userId")
    public ResponseEntity<List<GetAppointmentResponseDTO>> getPatientAppointments(@PathVariable(name = "patientId") String patientId ) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .body(
                        patientService.getPatientAppointments(patientId)
                );

    }

    @DeleteMapping("/{patientId}/deleteappointment/{appointmentId}")
    @PreAuthorize("hasRole('ROLE_PATIENT') and #patientId == authentication.principal.userId")
    public ResponseEntity<GetPatientResponseDTO> deletePatientAppointmentById(@PathVariable(name = "patientId") String patientId,@PathVariable(name = "appointmentId") String appointmentId ) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .body(
                        patientService.deletePatientAppointmentById(patientId,appointmentId)
                );

    }

}
