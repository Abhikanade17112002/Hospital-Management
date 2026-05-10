package com.hospitalmanagement.dtos.appointmentdtos;
import java.time.LocalDateTime;

public class AddAppointmentRequestDTO {
    private LocalDateTime appointmentTime ;
    private String reason;
    private String patientId ;
    private String doctorId ;

    public AddAppointmentRequestDTO(LocalDateTime appointmentTime, String reason, String patientId, String doctorId) {
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public AddAppointmentRequestDTO() {

    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "AddAppointmentRequestDTO{" +
                "appointmentTime=" + appointmentTime +
                ", reason='" + reason + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }
}
