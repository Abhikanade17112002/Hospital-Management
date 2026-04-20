package com.hospitalmanagement.dtos.patientdtos;

import java.time.LocalDate;

public class PatientsBornAfterRequestDTO {
    private LocalDate bornAfter ;

    public PatientsBornAfterRequestDTO(LocalDate bornAfter) {
        this.bornAfter = bornAfter;
    }

    public PatientsBornAfterRequestDTO() {
    }

    public LocalDate getBornAfter() {
        return bornAfter;
    }

    public void setBornAfter(LocalDate bornAfter) {
        this.bornAfter = bornAfter;
    }

    @Override
    public String toString() {
        return "PatientsBornAfterRequestDTO{" +
                "bornAfter=" + bornAfter +
                '}';
    }
}
