package com.hospitalmanagement.dtos.patientdtos;

import java.time.LocalDate;

public class PatientsDOBRequestDTO {
    private LocalDate startDate ;
    private LocalDate endDate ;

    public PatientsDOBRequestDTO() {
    }

    public PatientsDOBRequestDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "PatientsDOBRequestDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
