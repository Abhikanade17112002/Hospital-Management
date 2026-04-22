package com.hospitalmanagement.services.doctorservices;


import com.hospitalmanagement.dtos.doctordtos.AddDoctorRequestDTO;
import com.hospitalmanagement.dtos.doctordtos.AddDoctorResponseDTO;
import com.hospitalmanagement.entities.Doctor;
import com.hospitalmanagement.repositories.DoctorRepository;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository ;
    private final ModelMapper modelMapper ;

    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    public  AddDoctorResponseDTO addNewDoctor(AddDoctorRequestDTO doctor) {
       Doctor newDoctor =  modelMapper.map(doctor, Doctor.class) ;
       return modelMapper.map(doctorRepository.save(newDoctor) , AddDoctorResponseDTO.class)  ;
    }
}
