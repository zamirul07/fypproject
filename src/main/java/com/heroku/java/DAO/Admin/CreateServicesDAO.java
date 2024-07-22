package com.heroku.java.DAO.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Services;

@Repository
public class CreateServicesDAO {
    
    private final DataSource dataSource;

    @Autowired
    public CreateServicesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addServices(Services services) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertServicesSql = "INSERT INTO services (service_name, service_type, service_desc)"
                    + " VALUES (?, ?, ?)";
        
            PreparedStatement insertStatement = connection.prepareStatement(insertServicesSql);
            insertStatement.setString(1, services.getService_name());
            insertStatement.setString(2, services.getService_type());
            insertStatement.setString(3, services.getService_desc());
            

            System.out.println("services apa ? :" + services.getService_name());

            insertStatement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteServices(int service_id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String deleteServicesSql = "DELETE FROM services WHERE service_id = ?";
            
            PreparedStatement deleteStatement = connection.prepareStatement(deleteServicesSql);
            deleteStatement.setInt(1, service_id);

            deleteStatement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateServices(int service_id, String service_name, String service_desc) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String updateServicesSql = "UPDATE services SET service_name = ?, service_desc = ? WHERE service_id = ?";
            
            PreparedStatement updateStatement = connection.prepareStatement(updateServicesSql);
            updateStatement.setString(1, service_name);
            updateStatement.setString(2, service_desc);
            updateStatement.setInt(3, service_id);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
