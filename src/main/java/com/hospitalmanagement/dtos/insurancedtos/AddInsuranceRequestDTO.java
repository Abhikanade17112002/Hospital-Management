package com.hospitalmanagement.dtos.insurancedtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AddInsuranceRequestDTO {
    @NotBlank(message = "Policy Number Cannot Be Blank")
    private String policyNumber ;
    @NotBlank(message = "Policy Provider Cannot Be Blank")
    private String provider ;

    public AddInsuranceRequestDTO() {

    }

    public AddInsuranceRequestDTO(String policyNumber, String provider) {
        this.policyNumber = policyNumber;
        this.provider = provider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber( String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public  String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "AddInsuranceRequestDTO{" +
                "policyNumber='" + policyNumber + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
