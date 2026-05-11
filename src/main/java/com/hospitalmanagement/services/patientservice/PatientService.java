package com.hospitalmanagement.services.patientservice;

import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentRequestDTO;
import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.*;
import com.hospitalmanagement.enums.AppointmentStatus;
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
    GetPatientResponseDTO updatePatientProfile(String patientId, UpdatePatientProfileRequestDTO updatePatientProfileRequestDTO);
    GetAppointmentResponseDTO requestDoctorAppointment(AddAppointmentRequestDTO addAppointmentRequestDTO);
    List<GetAppointmentResponseDTO> getPatientAppointments(String patientId);
    GetPatientResponseDTO deletePatientAppointmentById(String patientId, String appointmentId);
    List<GetAppointmentResponseDTO> getPatientAppointmentsByStatus(String patientId, AppointmentStatus appointmentStatus);
}
