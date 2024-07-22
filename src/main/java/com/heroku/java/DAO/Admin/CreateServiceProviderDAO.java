package com.heroku.java.DAO.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heroku.java.Model.ServiceProvider;

@Repository
public class CreateServiceProviderDAO {

    private final DataSource dataSource;

    @Autowired
    public CreateServiceProviderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addServiceprovider(ServiceProvider serviceProvider) throws SQLException {
        String insertServiceProviderSql = "INSERT INTO serviceprovider (spfullname, email, password, address, icnumber, phonenumber, service_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(insertServiceProviderSql)) {
    
            insertStatement.setString(1, serviceProvider.getSpfullname());
            insertStatement.setString(2, serviceProvider.getEmail());
            insertStatement.setString(3, serviceProvider.getPassword());
            insertStatement.setString(4, serviceProvider.getAddress());
            insertStatement.setString(5, serviceProvider.getIcnumber());
            insertStatement.setString(6, serviceProvider.getPhonenumber());
            insertStatement.setString(7, serviceProvider.getService_name());
    
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
