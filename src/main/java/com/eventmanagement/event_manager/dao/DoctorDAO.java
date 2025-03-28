package com.eventmanagement.event_manager.dao;

import com.eventmanagement.event_manager.models.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                doctors.add(new Doctor(id, name, specialization));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                doctor = new Doctor(id, name, specialization);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}

