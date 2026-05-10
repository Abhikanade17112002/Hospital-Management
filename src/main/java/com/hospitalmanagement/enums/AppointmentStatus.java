package com.hospitalmanagement.enums;
public enum AppointmentStatus {
    PENDING,        // Appointment requested but not yet confirmed
    CONFIRMED,      // Confirmed by the doctor/admin
    CANCELLED,      // Cancelled by patient or doctor
    COMPLETED,      // Appointment has taken place
    NO_SHOW,        // Patient didn't show up
    RESCHEDULED     // Moved to a different time slot
}