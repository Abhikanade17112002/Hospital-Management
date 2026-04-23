package com.hospitalmanagement.services.patientservice.impl;

import com.hospitalmanagement.dtos.patientdtos.AddPatientRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.PatientsBornAfterRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.PatientsDOBRequestDTO;
import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import com.hospitalmanagement.services.patientservice.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    public PatientServiceImp(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetPatientResponseDTO registerPatient(AddPatientRequestDTO patient) {
        Patient newPatient = modelMapper.map(patient, Patient.class);
        Patient savedPatient = patientRepository.save(newPatient);
        return modelMapper.map(savedPatient, GetPatientResponseDTO.class);
    }

    @Override
    public List<GetPatientResponseDTO> getRegisteredPatients() {
//        List<Patient> retrivedPatients = patientRepository.findAll();
        List<Patient> retrivedPatients = patientRepository.getAllPatientsWithThereAppointments();
        List<GetPatientResponseDTO> response = new ArrayList<>();
        for (Patient patient : retrivedPatients) {
            response.add(
                    modelMapper.map(patient, GetPatientResponseDTO.class)
            );
        }

        return response;
    }

    @Override
    public GetPatientResponseDTO getRegisteredPatientById(String patientId) {
        Patient retrivedPatient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient With Id " + patientId + " Not Found"));
        GetPatientResponseDTO response = modelMapper.map(retrivedPatient, GetPatientResponseDTO.class) ;
        return response;
    }


    @Override
    public List<GetPatientResponseDTO> getPatientsByFirstNameContainingAndOrderByFirstName(String firstName) {
        List<Patient> retrivedPatients = patientRepository.findByFirstNameContainingOrderByFirstName(firstName);
        List<GetPatientResponseDTO> response = new ArrayList<>() ;
        for ( Patient patient : retrivedPatients ){
         response.add(
                 modelMapper.map(patient, GetPatientResponseDTO.class)
         )    ;
        }
        return response ;
    }

    @Override
    public List<GetPatientResponseDTO> getPatientsByDateOfBirthBetween(LocalDate startDate ,LocalDate endDate ) {
        List<Patient> retrivedPatients = patientRepository.findByDateOfBirthBetween(startDate,endDate);
        List<GetPatientResponseDTO> response = new ArrayList<>() ;
        for ( Patient patient : retrivedPatients ){
            response.add(
                    modelMapper.map(patient, GetPatientResponseDTO.class)
            )    ;
        }
        return response ;
    }

    @Override
    public List<GetPatientResponseDTO> getRegisteredPatientBornAfter(LocalDate bornAfter) {
        List<Patient> retrivedPatients = patientRepository.findPatientsBornAfter(bornAfter);
        List<GetPatientResponseDTO> response = new ArrayList<>() ;
        for ( Patient patient : retrivedPatients ){
            response.add(
                    modelMapper.map(patient, GetPatientResponseDTO.class)
            )    ;
        }
        return response ;
    }

    @Override
    public List<GetPatientResponseDTO> getRegisteredPatientBornAfterNative(LocalDate bornAfter) {
        List<Patient> retrivedPatients = patientRepository.findPatientsBornAfterNative(bornAfter);
        List<GetPatientResponseDTO> response = new ArrayList<>() ;
        for ( Patient patient : retrivedPatients ){
            response.add(
                    modelMapper.map(patient, GetPatientResponseDTO.class)
            )    ;
        }
        return response ;
    }

    @Override
    public List<Object[]> getGenderPatientCount() {
       return patientRepository.getGenderPatientCount() ;
    }

    @Override
    public List<GetPatientResponseDTO> getPagablePatients(int pageNo, int noOfRecords, String sortBy) {
       Page<Patient> retrivedPatients =  patientRepository.getPaginatedPatients(PageRequest.of(pageNo,noOfRecords, Sort.by(sortBy)));
       List<GetPatientResponseDTO> response = new ArrayList<>() ;

       for( Patient patient : retrivedPatients){
           response.add(
                   modelMapper.map(
                           patient, GetPatientResponseDTO.class
                   )
           ) ;
       }

       return response;
    }
}
