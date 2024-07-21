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
public class ServicesCusDAO {
private final DataSource dataSource;

public ServicesCusDAO(DataSource dataSource) {
    this.dataSource = dataSource;
}

public List<Services> viewServices() throws SQLException{
    List<Services> servicesList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

            while (resultSet.next()) {
                Integer service_id = resultSet.getInt("service_id");
                String service_name = resultSet.getString("service_name");
                String service_type = resultSet.getString("service_type");
                String service_desc = resultSet.getString("service_desc");
                

                Services services = new Services(service_id, service_name, service_type, service_desc);
                servicesList.add(services);
            }
        } catch (SQLException e) {
            throw e;
        }
        return servicesList;
    }

    public List<Services> getViewServices() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
