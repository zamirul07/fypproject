package com.heroku.java.DAO.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.ServiceProvider;


@Repository
public class ViewServiceProviderDAO {
private final DataSource dataSource;

public ViewServiceProviderDAO(DataSource dataSource) {
    this.dataSource = dataSource;
}

public List<ServiceProvider> viewServiceProvider() throws SQLException{
    List<ServiceProvider> serviceproviderList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM serviceprovider");

            while (resultSet.next()) {
                Integer sid = resultSet.getInt("sid");
                String spfullname = resultSet.getString("spfullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String icnumber = resultSet.getString("icnumber");
                String phonenumber = resultSet.getString("phonenumber");
                String service_name = resultSet.getString("service_name");                
                

                ServiceProvider serviceprovider = new ServiceProvider(sid, spfullname, email, password, address, icnumber, phonenumber, service_name);
                serviceproviderList.add(serviceprovider);
            }
        } catch (SQLException e) {
            throw e;
        }
        return serviceproviderList;
    }

    public List<ServiceProvider> getviewServiceProvider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}