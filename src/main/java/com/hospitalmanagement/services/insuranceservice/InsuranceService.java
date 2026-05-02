package com.hospitalmanagement.services.insuranceservice;

import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceRequestDTO;

public interface InsuranceService {
    public Boolean addInsuranceToPatientWithId(String patientId , AddInsuranceRequestDTO insurance) ;
}
