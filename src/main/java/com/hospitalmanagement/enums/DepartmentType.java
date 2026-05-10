package com.hospitalmanagement.enums;

public enum DepartmentType {

    OPD("Outpatient DepartmentType"),
    IPD("Inpatient DepartmentType"),
    EMERGENCY("Emergency & Trauma"),
    ICU("Intensive Care Unit"),
    NICU("Neonatal Intensive Care Unit"),

    CARDIOLOGY("Cardiology DepartmentType"),
    NEUROLOGY("Neurology DepartmentType"),
    ORTHOPEDICS("Orthopedics DepartmentType"),
    PEDIATRICS("Pediatrics DepartmentType"),
    GYNECOLOGY("Gynecology & Obstetrics"),

    ONCOLOGY("Oncology (Cancer Care)"),
    DERMATOLOGY("Dermatology"),
    ENT("Ear, Nose & Throat"),

    RADIOLOGY("Radiology & Imaging"),
    PATHOLOGY("Pathology & Diagnostics"),
    LABORATORY("Laboratory Services"),

    PHARMACY("Pharmacy"),
    BLOOD_BANK("Blood Bank"),

    ANESTHESIOLOGY("Anesthesiology"),
    PHYSIOTHERAPY("Physiotherapy & Rehabilitation"),

    ADMINISTRATION("Hospital Administration"),
    BILLING("Billing & Accounts"),
    MEDICAL_RECORDS("Medical Records DepartmentType");

    private final String displayName;

    DepartmentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}