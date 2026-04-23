package com.hospitalmanagement.repositories.patientrepository;


import com.hospitalmanagement.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public interface PatientRepository extends JpaRepository<Patient,String> {
    // JPA Query Methodes

    List<Patient> findByDateOfBirthBetween(LocalDate start , LocalDate end ) ;

    List<Patient> findByFirstNameContainingOrderByFirstName(String firstName) ;


    final String findPatientsBornAfter =
            "SELECT p FROM patient p WHERE p.dateOfBirth > :birthDate";

    @Query(findPatientsBornAfter)
    List<Patient> findPatientsBornAfter(@Param("birthDate") LocalDate birthDate);


    final String countPatientsPerGender =
            "SELECT p.gender , Count(p) FROM patient p GROUP BY p.gender";
    @Query(countPatientsPerGender)
    List<Object[]> getGenderPatientCount();

    final String findPatientsBornAfterNative =
            "SELECT * FROM patient p WHERE p.date_of_birth > :birthDate";
    @Query(value = findPatientsBornAfterNative,nativeQuery = true)
    List<Patient> findPatientsBornAfterNative(@Param("birthDate") LocalDate birthDate);


    // Pagination

    final String getPaginatedPatientsQuery =  " SELECT P FROM patient P" ;
    @Query(getPaginatedPatientsQuery)
    Page<Patient> getPaginatedPatients(Pageable page) ;


    // N+1 Query

//    @Query("SELECT P FROM patient P LEFT JOIN FETCH P.appointments a LEFT JOIN FETCH a.doctor")
@Query("SELECT P FROM patient P LEFT JOIN FETCH P.appointments")
    List<Patient> getAllPatientsWithThereAppointments() ;


// Projection

//    @Query("SELECT new com.hospitalmanagement.dtos.PatientNameDTO(p.firstName, p.lastName) FROM patient p WHERE p.dateOfBirth > :birthDate")
//    List<PatientNameDTO> findPatientNamesDTO(@Param("birthDate") LocalDate birthDate);
}
