package com.hospitalmanagement.services.insuranceservice;

import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceRequestDTO;
import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface InsuranceService {
    public GetPatientResponseDTO addInsuranceToPatientWithId(String patientId , AddInsuranceRequestDTO insurance) ;
    public AddInsuranceResponseDTO createInsurance(AddInsuranceRequestDTO addInsuranceRequestDTO);
    public List<AddInsuranceResponseDTO> getAllInsurance();
    public GetPatientResponseDTO removeInsuranceToPatientWithId(String patientId);
}
