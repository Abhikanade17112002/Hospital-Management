package com.hospitalmanagement.enums;

public enum PermissionType {

    USER_CREATE("USER:CREATE"),
    USER_READ("USER:READ"),
    USER_UPDATE("USER:UPDATE"),
    USER_DELETE("USER:DELETE"),
    DOCTOR_CREATE("DOCTOR:CREATE"),
    DOCTOR_READ("DOCTOR:READ"),
    DOCTOR_UPDATE("DOCTOR:UPDATE"),
    DOCTOR_DELETE("DOCTOR:DELETE"),
    PATIENT_CREATE("PATIENT:CREATE"),
    PATIENT_READ("PATIENT:READ"),
    PATIENT_UPDATE("PATIENT:UPDATE"),
    PATIENT_DELETE("PATIENT:DELETE"),
    APPOINTMENT_CREATE("APPOINTMENT:CREATE"),
    APPOINTMENT_READ("APPOINTMENT:READ"),
    APPOINTMENT_UPDATE("APPOINTMENT:UPDATE"),
    APPOINTMENT_DELETE("APPOINTMENT:DELETE"),
    APPOINTMENT_CANCEL("APPOINTMENT:CANCEL");

    private final String permission;

    PermissionType(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return "PermissionType{" +
                "permission='" + permission + '\'' +
                '}';
    }
}