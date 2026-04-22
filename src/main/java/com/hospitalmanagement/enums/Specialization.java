package com.hospitalmanagement.enums;

public enum Specialization {

    CARDIOLOGY("Cardiology"),
    NEUROLOGY("Neurology"),
    ORTHOPEDICS("Orthopedics"),
    PEDIATRICS("Pediatrics"),
    DERMATOLOGY("Dermatology"),
    GYNECOLOGY("Gynecology & Obstetrics"),

    ONCOLOGY("Oncology"),
    PSYCHIATRY("Psychiatry"),
    UROLOGY("Urology"),
    GASTROENTEROLOGY("Gastroenterology"),
    ENDOCRINOLOGY("Endocrinology"),
    NEPHROLOGY("Nephrology"),

    PULMONOLOGY("Pulmonology"),
    ANESTHESIOLOGY("Anesthesiology"),
    RADIOLOGY("Radiology"),
    PATHOLOGY("Pathology"),

    GENERAL_MEDICINE("General Medicine"),
    GENERAL_SURGERY("General Surgery"),
    ENT("Ear, Nose & Throat");

    private final String displayName;

    Specialization(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}