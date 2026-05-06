package com.hospitalmanagement.services.doctorservices;


import com.hospitalmanagement.dtos.doctordtos.AddDoctorRequestDTO;
import com.hospitalmanagement.dtos.doctordtos.AddDoctorResponseDTO;
import com.hospitalmanagement.dtos.doctordtos.GetDoctorResponseDTO;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.entities.Role;
import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.enums.RoleType;
import com.hospitalmanagement.repositories.DoctorRepository;
import com.hospitalmanagement.repositories.RoleRepository;
import com.hospitalmanagement.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
}
