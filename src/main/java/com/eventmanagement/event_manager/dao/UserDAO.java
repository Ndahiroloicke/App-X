package com.eventmanagement.event_manager.dao;

import com.eventmanagement.event_manager.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public Patient getUser(String email, String password) {
        Patient patient = null;
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                patient = new Patient(rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public boolean createUser(Patient patient) {
        String sql = "INSERT INTO users(email, password) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getUsername());
            stmt.setString(2, patient.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

