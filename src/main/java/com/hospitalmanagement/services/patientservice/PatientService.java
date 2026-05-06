package com.hospitalmanagement.services.patientservice;

import com.hospitalmanagement.dtos.patientdtos.AddPatientRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.GetPatientResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.PatientsBornAfterRequestDTO;
import com.hospitalmanagement.dtos.patientdtos.PatientsDOBRequestDTO;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


public interface PatientService {
    GetPatientResponseDTO registerPatient(@Valid AddPatientRequestDTO patient);
    List<GetPatientResponseDTO> getRegisteredPatients() ;
    GetPatientResponseDTO getRegisteredPatientById(String patientId) ;
    List<GetPatientResponseDTO> getPatientsByFirstNameContainingAndOrderByFirstName(String firstName);
    List<GetPatientResponseDTO> getPatientsByDateOfBirthBetween(LocalDate startDate , LocalDate endDate);
    List<GetPatientResponseDTO> getRegisteredPatientBornAfter(LocalDate bornAfter);
    List<GetPatientResponseDTO> getRegisteredPatientBornAfterNative(LocalDate bornAfter);
    List<Object[]> getGenderPatientCount();
    List<GetPatientResponseDTO> getPagablePatients(int pageNo, int noOfRecords, String sortBy);
    String deleteRegisteredPatientById(String patientId);
}
