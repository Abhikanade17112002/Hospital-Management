package com.hospitalmanagement.controllers.insurancecontroller;



import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceRequestDTO;
import com.hospitalmanagement.services.insuranceservice.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService ;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/addinsurance/{patientId}")
    public ResponseEntity<Boolean> addInsuranceToParientWithId(@PathVariable( name ="patientId" ) String patientId , @RequestBody AddInsuranceRequestDTO addInsuranceRequestDTO){
        return ResponseEntity.status(
                HttpStatus.OK
        )
                .body(
                        insuranceService.addInsuranceToPatientWithId(patientId, addInsuranceRequestDTO)
                );
    }
}
