package com.heroku.java.DAO.Customer;

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
public class CustomerViewServiceProviderDAO {
private final DataSource dataSource;

public CustomerViewServiceProviderDAO(DataSource dataSource) {
    this.dataSource = dataSource;
}

public List<ServiceProvider> customerviewServiceProvider() throws SQLException{
    List<ServiceProvider> serviceproviderList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM serviceprovider");

            while (resultSet.next()) {
                Integer sid = resultSet.getInt("sid");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String icnumber = resultSet.getString("icnumber");
                String phonenumber = resultSet.getString("phonenumber");
                String services = resultSet.getString("services");                
                

                ServiceProvider serviceprovider = new ServiceProvider(sid, fullname, email, password, address, icnumber, phonenumber, services);
                serviceproviderList.add(serviceprovider);
            }
        } catch (SQLException e) {
            throw e;
        }
        return serviceproviderList;
    }

    public List<ServiceProvider> getcustomerviewServiceProvider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}