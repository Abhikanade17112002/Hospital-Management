package com.hospitalmanagement.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Insurance {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private String insuranceId ;

    @Column( nullable = false , unique = true)
    private String policyNumber ;

    @Column( nullable = false)
    private String provider ;

    @CreationTimestamp
    @Column( nullable = false , updatable = false)
    private LocalDateTime createdAt ;

    @OneToOne( mappedBy = "insurance")
    private Patient patient ;

    public Insurance() {
    }

    public Insurance(String insuranceId, String policyNumber, String provider, LocalDateTime createdAt, Patient patient) {
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
        return "Insurance{" +
                "insuranceId='" + insuranceId + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", provider='" + provider + '\'' +
                ", createdAt=" + createdAt +
                ", patient=" + patient +
                '}';
    }
}
