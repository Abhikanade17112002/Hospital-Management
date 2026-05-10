package com.hospitalmanagement.dtos.insurancedtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalmanagement.entities.Patient;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class AddInsuranceResponseDTO {
    private String insuranceId ;
    private String policyNumber ;
    private String provider ;
    private LocalDateTime createdAt ;
    private Patient patient ;

    public AddInsuranceResponseDTO() {
    }

    public AddInsuranceResponseDTO(String insuranceId, String policyNumber, String provider, LocalDateTime createdAt, Patient patient) {
        this.insuranceId = insuranceId;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.createdAt = createdAt;
        this.patient = patient;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "AddInsuranceResponseDTO{" +
                "insuranceId='" + insuranceId + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", provider='" + provider + '\'' +
                ", createdAt=" + createdAt +
                ", patient=" + patient +
                '}';
    }
}
