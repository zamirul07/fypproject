package com.heroku.java.DAO.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Services;

@Repository
public class ServicesDAO {

    private final DataSource dataSource;

    public ServicesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Services> viewServices() throws SQLException {
        List<Services> servicesList = new ArrayList<>();
        String query = "SELECT * FROM services";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                Services services = new Services();
                services.setService_id(resultSet.getInt("service_id"));
                services.setService_name(resultSet.getString("service_name"));
                services.setService_type(resultSet.getString("service_type"));
                services.setService_desc(resultSet.getString("service_desc"));
                servicesList.add(services);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return servicesList;
    }

    public List<Services> getAllServices() throws SQLException {
        List<Services> services = new ArrayList<>();
        String selectAllServicesSql = "SELECT * FROM services";
        try (Connection connection = dataSource.getConnection();
             Statement selectStatement = connection.createStatement();
             ResultSet resultSet = selectStatement.executeQuery(selectAllServicesSql)) {
            
            while (resultSet.next()) {
                Services service = new Services();
                service.setService_id(resultSet.getInt("service_id"));
                service.setService_name(resultSet.getString("service_name"));
                service.setService_desc(resultSet.getString("service_desc"));
                service.setService_type(resultSet.getString("service_type"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return services;
    }

    
}
