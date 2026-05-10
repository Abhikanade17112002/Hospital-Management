package com.hospitalmanagement.controllers.insurancecontroller;



import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceRequestDTO;
import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.services.insuranceservice.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService ;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AddInsuranceResponseDTO> createInsurance(@RequestBody AddInsuranceRequestDTO addInsuranceRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(insuranceService.createInsurance(addInsuranceRequestDTO));
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AddInsuranceResponseDTO>> getAllInsurance(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(insuranceService.getAllInsurance());
    }

    @PostMapping("/addinsurance/{patientId}")
    @PreAuthorize("hasRole('ROLE_PATIENT') and (#patientId == authentication.principal.userId)")
    public ResponseEntity<GetPatientResponseDTO> addInsuranceToPatientWithId(@PathVariable( name ="patientId" ) String patientId , @RequestBody AddInsuranceRequestDTO addInsuranceRequestDTO){
        return ResponseEntity.status(
                HttpStatus.OK
        )
                .body(
                        insuranceService.addInsuranceToPatientWithId(patientId, addInsuranceRequestDTO)
                );
    }

    @DeleteMapping("/removeinsurance/{patientId}")
    @PreAuthorize("hasRole('ROLE_PATIENT') and (#patientId == authentication.principal.userId)")
    public ResponseEntity<GetPatientResponseDTO> removeInsuranceToPatientWithId(@PathVariable( name ="patientId" ) String patientId){
        return ResponseEntity.status(
                        HttpStatus.OK
                )
                .body(
                        insuranceService.removeInsuranceToPatientWithId(patientId)
                );
    }
}
