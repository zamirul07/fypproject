package com.heroku.java.DAO.ServiceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.ServiceProvider;

@Repository
public class ServiceProviderProfileDAO {
    private final DataSource dataSource;

    public ServiceProviderProfileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ServiceProvider ServiceProviderProfile(int sid) throws SQLException {
        // Your database connection and query code
        // Example implementation
        ServiceProvider serviceprovider = null;
        String query = "SELECT * FROM serviceprovider WHERE sid = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    serviceprovider = new ServiceProvider();
                    serviceprovider.setSid(resultSet.getInt("sid"));
                    serviceprovider.setSpfullname(resultSet.getString("spfullname"));
                    serviceprovider.setEmail(resultSet.getString("email"));
                    serviceprovider.setPassword(resultSet.getString("password"));
                    serviceprovider.setAddress(resultSet.getString("address"));
                    serviceprovider.setIcnumber(resultSet.getString("icnumber"));
                    serviceprovider.setPhonenumber(resultSet.getString("phonenumber"));
                    serviceprovider.setService_name(resultSet.getString("service_name"));
                    
                }
            }
        }
        return serviceprovider;
    }


    public ServiceProvider updateProfile(ServiceProvider serviceprovider) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE serviceprovider SET spfullname=?, email=?, password=?, address=?, icnumber=?, phonenumber=?, services=? WHERE sid=?";
            final var statement = connection.prepareStatement(sql);
    
            String password = serviceprovider.getPassword();
            System.out.println("password: " + password);
    
            statement.setString(1, serviceprovider.getSpfullname());
            statement.setString(2, serviceprovider.getEmail());
            statement.setString(3, serviceprovider.getPassword());
            statement.setString(4, serviceprovider.getAddress());
            statement.setString(5, serviceprovider.getIcnumber());
            statement.setString(6, serviceprovider.getPhonenumber());
            statement.setString(7, serviceprovider.getService_name());
            statement.setInt(8, serviceprovider.getSid());
    
            statement.executeUpdate();
        }
        return serviceprovider;
    }

    public void deleteProfile(int sid) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String deleteServiceProvidersql = "DELETE FROM serviceprovider WHERE sid = ?";

            PreparedStatement deleteStatement = connection.prepareStatement(deleteServiceProvidersql);
            deleteStatement.setInt(1, sid);
            deleteStatement.execute();

        } catch (SQLException e) {
            throw e;
        }
        }
    }
