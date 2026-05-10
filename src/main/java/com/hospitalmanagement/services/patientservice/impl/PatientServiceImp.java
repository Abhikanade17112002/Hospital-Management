package com.hospitalmanagement.services.patientservice.impl;

import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentRequestDTO;
import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.dtos.patientdtos.*;
import com.hospitalmanagement.entities.Appointment;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.AppointmentStatus;
import com.hospitalmanagement.repositories.AppointmentRepository;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.repositories.UserRepository;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import com.hospitalmanagement.services.patientservice.PatientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
import java.util.stream.Collectors;

@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository ;
    private  final DoctorRepository doctorRepository ;
    private final AppointmentRepository appointmentRepository ;

    public PatientServiceImp(PatientRepository patientRepository, ModelMapper modelMapper, UserRepository userRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public GetPatientResponseDTO registerPatient(AddPatientRequestDTO patient) {
        Patient newPatient = modelMapper.map(patient, Patient.class);
        Patient savedPatient = patientRepository.save(newPatient);
        return modelMapper.map(savedPatient, GetPatientResponseDTO.class);
    }

    @Override
    public List<GetPatientResponseDTO> getRegisteredPatients() {
        List<Patient> retrivedPatients = patientRepository.findAll();
        List<GetPatientResponseDTO> response = retrivedPatients.stream().map((patient)-> modelMapper.map(patient, GetPatientResponseDTO.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public GetPatientResponseDTO getRegisteredPatientById(String patientId) {
        Patient retrivedPatient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient With Id " + patientId + " Not Found"));
        User user = retrivedPatient.getUser() ;
        if( !user.isProfileComplete() ){
            throw  new RuntimeException("Patient Profile Is Incomplete, Please Complete To Proceed");
        }
        return modelMapper.map(retrivedPatient, GetPatientResponseDTO.class) ;
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

    @Override
    public String deleteRegisteredPatientById(String patientId) {
        Patient retrivedPatient = patientRepository.findById(patientId).orElseThrow(()-> new EntityNotFoundException("Patient With Patient Id ==>" + patientId + " Not Found"));
        User user = retrivedPatient.getUser() ;
        patientRepository.delete(retrivedPatient);
        userRepository.delete(user);
        return "Patient With Patient Id ==>" + patientId + " Deleted Successfully";
    }

    @Override
    public GetPatientResponseDTO updatePatientProfile(String patientId, UpdatePatientProfileRequestDTO updatePatientProfileRequestDTO) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient With Id " + patientId + " Not Found"));
        User user = patient.getUser() ;
        if (updatePatientProfileRequestDTO.getFirstName() != null) {
            patient.setFirstName(updatePatientProfileRequestDTO.getFirstName());
        }

        if (updatePatientProfileRequestDTO.getLastName() != null) {
            patient.setLastName(updatePatientProfileRequestDTO.getLastName());
        }

        if (updatePatientProfileRequestDTO.getDateOfBirth() != null) {
            patient.setDateOfBirth(updatePatientProfileRequestDTO.getDateOfBirth());
        }

        if (updatePatientProfileRequestDTO.getGender() != null) {
            patient.setGender(updatePatientProfileRequestDTO.getGender());
        }

        if (updatePatientProfileRequestDTO.getBloodGroup() != null) {
            patient.setBloodGroup(updatePatientProfileRequestDTO.getBloodGroup());
        }
        user.setProfileComplete(true);
        patient.setUser(user);
        return modelMapper.map(patientRepository.save(patient), GetPatientResponseDTO.class) ;
    }

    @Override
    public GetAppointmentResponseDTO requestDoctorAppointment(AddAppointmentRequestDTO addAppointmentRequestDTO) {
        Patient patient = patientRepository.findById(addAppointmentRequestDTO.getPatientId()).orElseThrow(()->new EntityNotFoundException("Patient With Id " + addAppointmentRequestDTO.getPatientId() + " Not Found"));
        Doctor doctor = doctorRepository.findById(addAppointmentRequestDTO.getDoctorId()).orElseThrow(()->new EntityNotFoundException("Doctor With Id " + addAppointmentRequestDTO.getDoctorId() + " Not Found"));
        Appointment appointment = new Appointment() ;
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(addAppointmentRequestDTO.getAppointmentTime());
        appointment.setReason(addAppointmentRequestDTO.getReason());
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);

        doctor.getAppointments().add(appointment);
        patient.getAppointments().add(appointment);

        return modelMapper.map(appointmentRepository.save(appointment), GetAppointmentResponseDTO.class);
    }

    @Override
    public List<GetAppointmentResponseDTO> getPatientAppointments(String patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException("Patient With Id " + patientId + " Not Found"));
        List<Appointment> appointments = appointmentRepository.findAllByPatient(patient);
        return appointments.stream().map((appointment)-> modelMapper.map(appointment, GetAppointmentResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GetPatientResponseDTO deletePatientAppointmentById(String patientId, String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + appointmentId));

        // Remove from both sides of the relationship
        Patient patient = appointment.getPatient();
        patient.getAppointments().remove(appointment);

        Doctor doctor = appointment.getDoctor();
        doctor.getAppointments().remove(appointment);

        appointmentRepository.deleteById(appointmentId);
        return modelMapper.map(patient, GetPatientResponseDTO.class);
    }
}
