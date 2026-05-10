package com.hospitalmanagement.services.doctorservices;


import com.hospitalmanagement.dtos.appointmentdtos.GetAppointmentResponseDTO;
import com.hospitalmanagement.dtos.doctordtos.AddDoctorRequestDTO;
import com.hospitalmanagement.dtos.doctordtos.AddDoctorResponseDTO;
import com.hospitalmanagement.dtos.doctordtos.GetDoctorResponseDTO;
import com.hospitalmanagement.dtos.doctordtos.UpdateDoctorProfileRequestDTO;
import com.hospitalmanagement.entities.*;
import com.hospitalmanagement.enums.RoleType;
import com.hospitalmanagement.repositories.AppointmentRepository;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.repositories.RoleRepository;
import com.hospitalmanagement.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository ;
    private final AppointmentRepository appointmentRepository ;

    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public AddDoctorResponseDTO addNewDoctor(AddDoctorRequestDTO doctor) {
        Doctor newDoctor = modelMapper.map(doctor, Doctor.class);
        return modelMapper.map(doctorRepository.save(newDoctor), AddDoctorResponseDTO.class);
    }

    public List<GetDoctorResponseDTO> getRegisteredDoctors() {
        List<Doctor> retrivedDoctors = doctorRepository.findAll();
        return retrivedDoctors.
                stream()
                .map((doctor) -> {
                    GetDoctorResponseDTO temp = new GetDoctorResponseDTO();
                    temp.setUser(doctor.getUser());
                    temp.setFirstName(doctor.getFirstName());
                    temp.setLastName(doctor.getLastName());
                    temp.setAppointments(doctor.getAppointments());
                    temp.setDepartments(doctor.getDepartments());
                    temp.setGender(doctor.getGender());
                    temp.setSpecialization(doctor.getSpecialization());
                    temp.setBloodGroup(doctor.getBloodGroup());
                    return temp;
                }).collect(Collectors.toList());
    }

    public GetDoctorResponseDTO getRegisteredDoctorById(String doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException());
        if( !doctor.getUser().isProfileComplete() ){
            throw new RuntimeException("Doctor Profile Is Not Complete, Please Complete To Continue");
        }
        return modelMapper.map(doctor, GetDoctorResponseDTO.class);
    }

    public String deleteDoctorById(String doctorId) {


        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor With Id ==> " + doctorId + " Not Found "));
        User user = doctor.getUser();
        doctorRepository.delete(doctor);
        userRepository.delete(user);

        return "Doctor With Id ==> " + doctorId + " Deleted Succesfully ";
    }

    public GetDoctorResponseDTO onBoardDoctorByUserId(String userId) {
        User retrivedUser = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User With User Id ==> " + userId + " Not Found"));
        Role doctorRole = roleRepository.findByRoleType(RoleType.ROLE_DOCTOR) ;
        retrivedUser.getRoles().add(doctorRole);
        Doctor doctor = new Doctor() ;
        doctor.setUser(retrivedUser);
        doctorRepository.save(doctor);
        return modelMapper.map(doctor, GetDoctorResponseDTO.class) ;
    }

    public GetDoctorResponseDTO updateDoctorProfile(String doctorId, UpdateDoctorProfileRequestDTO updateDoctorProfileRequestDTO) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor With Id " + doctorId + " Not Found"));
        User user = doctor.getUser() ;
        if (updateDoctorProfileRequestDTO.getFirstName() != null) {
            doctor.setFirstName(updateDoctorProfileRequestDTO.getFirstName());
        }

        if (updateDoctorProfileRequestDTO.getLastName() != null) {
            doctor.setLastName(updateDoctorProfileRequestDTO.getLastName());
        }

        if (updateDoctorProfileRequestDTO.getDateOfBirth() != null) {
            doctor.setDateOfBirth(updateDoctorProfileRequestDTO.getDateOfBirth());
        }

        if (updateDoctorProfileRequestDTO.getGender() != null) {
            doctor.setGender(updateDoctorProfileRequestDTO.getGender());
        }

        if (updateDoctorProfileRequestDTO.getBloodGroup() != null) {
            doctor.setBloodGroup(updateDoctorProfileRequestDTO.getBloodGroup());
        }
        if (updateDoctorProfileRequestDTO.getSpecialization() != null) {
            doctor.setSpecialization(updateDoctorProfileRequestDTO.getSpecialization());
        }
        user.setProfileComplete(true);
        doctor.setUser(user);
        return modelMapper.map(doctorRepository.save(doctor), GetDoctorResponseDTO.class) ;

    }

    public List<GetAppointmentResponseDTO> getDoctorAppointments(String doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new EntityNotFoundException("Doctor With Id " + doctorId + " Not Found"));
        List<Appointment> appointments = appointmentRepository.findAllByDoctor(doctor);
        return appointments.stream().map((appointment)-> modelMapper.map(appointment, GetAppointmentResponseDTO.class)).collect(Collectors.toList());
    }

    public GetDoctorResponseDTO deleteDoctorAppointmentById(String doctorId, String appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + appointmentId));

        // Remove from both sides of the relationship
        Patient patient = appointment.getPatient();
        patient.getAppointments().remove(appointment);

        Doctor doctor = appointment.getDoctor();
        doctor.getAppointments().remove(appointment);

        appointmentRepository.deleteById(appointmentId);
        return modelMapper.map(doctor, GetDoctorResponseDTO.class);
    }
}
