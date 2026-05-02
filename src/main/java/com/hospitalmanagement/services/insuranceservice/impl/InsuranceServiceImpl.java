package com.hospitalmanagement.services.insuranceservice.impl;


import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceRequestDTO;
import com.hospitalmanagement.entities.Insurance;
import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.repositories.InsuranceRepository;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import com.hospitalmanagement.services.insuranceservice.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository ;
    private final PatientRepository patientRepository ;


    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, PatientRepository patientRepository) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }

    public Boolean addInsuranceToPatientWithId(String patientId , AddInsuranceRequestDTO insurance){

        Patient retrivedPatient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient With Patient Id ==> " + patientId + " Not found")) ;

        Insurance newInsurance = new Insurance(insurance.getPolicyNumber(),insurance.getProvider()) ;

        retrivedPatient.setInsurance(newInsurance);

        patientRepository.save(retrivedPatient) ;

        return true ;

    }
}
