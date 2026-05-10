package com.hospitalmanagement.services.insuranceservice.impl;


import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceRequestDTO;
import com.hospitalmanagement.dtos.insurancedtos.AddInsuranceResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.entities.Insurance;
import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.repositories.InsuranceRepository;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import com.hospitalmanagement.services.insuranceservice.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository ;
    private final PatientRepository patientRepository ;
    private final ModelMapper modelMapper ;


    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    public GetPatientResponseDTO addInsuranceToPatientWithId(String patientId , AddInsuranceRequestDTO insurance){
        Patient retrivedPatient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient With Patient Id ==> " + patientId + " Not found")) ;
        Insurance reterivedInsurance = insuranceRepository.findByProviderAndPolicyNumber(insurance.getProvider(),insurance.getPolicyNumber()).orElseThrow(()->new EntityNotFoundException("Insurance With Provider " + insurance.getProvider() + " And Policy Number " + insurance.getPolicyNumber() + " Not Found" ));
        reterivedInsurance.setPatient(retrivedPatient);
        retrivedPatient.setInsurance(reterivedInsurance);
        return  modelMapper.map(patientRepository.save(retrivedPatient), GetPatientResponseDTO.class);

    }

    @Override
    public AddInsuranceResponseDTO createInsurance(AddInsuranceRequestDTO addInsuranceRequestDTO) {
        Insurance newInsurance = new Insurance() ;
        newInsurance.setProvider(addInsuranceRequestDTO.getProvider());
        newInsurance.setPolicyNumber(addInsuranceRequestDTO.getPolicyNumber());
        return modelMapper.map(insuranceRepository.save(newInsurance),AddInsuranceResponseDTO.class);
    }

    @Override
    public List<AddInsuranceResponseDTO> getAllInsurance() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        return insuranceList.stream().map((insurance)-> modelMapper.map(insurance, AddInsuranceResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public GetPatientResponseDTO removeInsuranceToPatientWithId(String patientId) {
        Patient retrivedPatient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient With Patient Id ==> " + patientId + " Not found")) ;
        Insurance insurance = retrivedPatient.getInsurance() ;
        insurance.setPatient(null);
        retrivedPatient.setInsurance(null);
        return modelMapper.map(patientRepository.save(retrivedPatient), GetPatientResponseDTO.class);
    }
}
