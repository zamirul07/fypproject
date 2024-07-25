package com.heroku.java.DAO.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Admin;

@Repository
public class ViewProfileAdminDAO {
    private final DataSource dataSource;

    public ViewProfileAdminDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Admin AdminProfile(int adminid) throws SQLException {
        // Your database connection and query code
        // Example implementation
        Admin admin = null;
        String query = "SELECT * FROM admin WHERE adminid = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adminid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    admin = new Admin();
                    admin.setAdminid(resultSet.getInt("adminid"));
                    admin.setAdminname(resultSet.getString("adminfullname"));
                    admin.setemail(resultSet.getString("email"));
                    admin.setpassword(resultSet.getString("password"));
                    
                    // Set other customer fields
                }
            }
        }
        return admin;
    }

    private String password() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'password'");
    }





}
