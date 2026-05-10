package com.hospitalmanagement.dtos.appointmentdtos;

import com.hospitalmanagement.enums.AppointmentStatus;

public class UpdateAppointmentStatus {
    private AppointmentStatus appointmentStatus ;

    public UpdateAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public UpdateAppointmentStatus() {
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "UpdateAppointmentStatus{" +
                "appointmentStatus=" + appointmentStatus +
                '}';
    }
}
