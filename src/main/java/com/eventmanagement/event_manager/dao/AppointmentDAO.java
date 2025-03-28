package com.eventmanagement.event_manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eventmanagement.event_manager.models.Appointment;
import com.eventmanagement.event_manager.models.Doctor;

public class AppointmentDAO {
    public boolean addAppointment(Appointment appointment, String userEmail) {
        String sql = "INSERT INTO appointments(doctor_id, appointment_date, user_email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getDoctor().getId());
            stmt.setTimestamp(2, new java.sql.Timestamp(appointment.getAppointmentDate().getTime()));
            stmt.setString(3, userEmail);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointment> getAppointmentsByUser(String userEmail) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.id, a.appointment_date, d.id as doctor_id, d.name, d.specialization " +
                "FROM appointments a JOIN doctors d ON a.doctor_id = d.id " +
                "WHERE a.user_email = ? ORDER BY a.appointment_date";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int doctorId = rs.getInt("doctor_id");
                String doctorName = rs.getString("name");
                String specialization = rs.getString("specialization");
                Date appointmentDate = new Date(rs.getTimestamp("appointment_date").getTime());
                Doctor doctor = new Doctor(doctorId, doctorName, specialization);
                Appointment appointment = new Appointment(id, doctor, appointmentDate);
                appointments.add(appointment);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public boolean deleteAppointment(int appointmentId, String userEmail) {
        String sql = "DELETE FROM appointments WHERE id = ? AND user_email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            stmt.setString(2, userEmail);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAppointment(int appointmentId, Date newDate, String userEmail) {
        String sql = "UPDATE appointments SET appointment_date = ? WHERE id = ? AND user_email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new java.sql.Timestamp(newDate.getTime()));
            stmt.setInt(2, appointmentId);
            stmt.setString(3, userEmail);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
