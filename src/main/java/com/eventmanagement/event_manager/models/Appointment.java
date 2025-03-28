package com.eventmanagement.event_manager.models;


import java.util.Date;

public class Appointment {
    private int id;
    private Doctor doctor;
    private Date appointmentDate;

    public Appointment(int id, Doctor doctor, Date appointmentDate) {
        this.id = id;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(Doctor doctor, Date appointmentDate) {
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }
}
