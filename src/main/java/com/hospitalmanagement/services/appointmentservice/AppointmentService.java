package com.hospitalmanagement.services.appointmentservice;

import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentRequestDTO;
import com.hospitalmanagement.dtos.appointmentdtos.AddAppointmentResponseDTO;
import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.entities.Appointment;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.repositories.AppointmentRepository;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.repositories.patientrepository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository ;
    private final ModelMapper modelMapper ;
    private final DoctorRepository doctorRepository ;
    private final PatientRepository patientRepository ;

    public AppointmentService(AppointmentRepository appointmentRepository, ModelMapper modelMapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }


    public  AddAppointmentResponseDTO addNewAppointment(AddAppointmentRequestDTO appointment) {
        Doctor retrivedDoctor = doctorRepository.findById(appointment.getDoctorId())
                .orElseThrow(()-> new EntityNotFoundException("Doctor With Doctor Id ==> " + appointment.getDoctorId() + " Not Found"));
        Patient retrivedPatient = patientRepository.findById(appointment.getPatientId())
                .orElseThrow(()-> new EntityNotFoundException("Doctor With Patient Id ==> " + appointment.getPatientId() + " Not Found"));

        Appointment newAppointment = modelMapper.map(appointment, Appointment.class) ;
        newAppointment.setPatient(retrivedPatient);
        newAppointment.setDoctor(retrivedDoctor);
        appointmentRepository.save(newAppointment);

        retrivedDoctor.getAppointments().add(newAppointment);
        retrivedPatient.getAppointments().add(newAppointment);


        AddAppointmentResponseDTO response = modelMapper.map(newAppointment, AddAppointmentResponseDTO.class) ;
        return response ;

    }

    public List<GetAppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream().map((appointment -> modelMapper.map(appointment, GetAppointmentResponseDTO.class))).collect(Collectors.toList());
    }

    public GetAppointmentResponseDTO getAppointmentById(String appointmentId) {
        Appointment retreivedAppointment = appointmentRepository.findById(appointmentId).orElseThrow(()-> new EntityNotFoundException("Appointment With Appointment Id ==> " + appointmentId + " Not Found"));
        return modelMapper.map(retreivedAppointment, GetAppointmentResponseDTO.class) ;
    }
}
