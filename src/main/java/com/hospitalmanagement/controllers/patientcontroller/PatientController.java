package com.hospitalmanagement.controllers.patientcontroller;


import com.hospitalmanagement.dtos.patientdtos.AddPatientRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.PatientsBornAfterRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.PatientsDOBRequestDTO;
import com.hospitalmanagement.services.patientservice.PatientService;
import jakarta.validation.Valid;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GetPatientResponseDTO> getRegisteredPatientById(@PathVariable(name = "patientId") String patientId ) {
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        patientService.getRegisteredPatientById(patientId)
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

}
