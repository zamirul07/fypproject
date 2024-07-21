package com.heroku.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Admin;
import com.heroku.java.Model.Customer;
import com.heroku.java.Model.ServiceProvider;

@Repository
public class LoginDAO {
    private DataSource dataSource;

    public LoginDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //model V
    public Customer checkCustomer(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {

            // System.out.println("Email Customer : "+ email );
            // System.out.println("Password Customer : "+ password);
            // System.out.println("username : "+ username);

            String sql = "SELECT id, customerfullname FROM customer WHERE email = ? AND password = ?";// check attribute database
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            //selagi db tu baca dia amik benda tu yang declare 
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerfullname = resultSet.getString("customerfullname");
                // attribute dia amik dari db pass ke controller
                return new Customer(id, customerfullname, email, password);
            }
            connection.close();
        } 
        return null;
    }

    public Admin checkAdmin(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            // System.out.println("email : "+ adminemail);
            // System.out.println("password : "+ adminpassword);

            String sql = "SELECT adminid, adminfullname FROM admin WHERE email = ? AND password = ? ";// check attribute database
            PreparedStatement statement = connection.prepareStatement(sql);
            // select where
            statement.setString(1, email);
            statement.setString(2, password);
            // statement.setString(3, role);
            ResultSet resultSet = statement.executeQuery();
            
            //selagi db tu baca dia amik benda tu yang declare 
            if (resultSet.next()) {
                // kena declare mana yang amik
                int adminid = resultSet.getInt("adminid");
                String adminfullname = resultSet.getString("adminfullname");
                // attribute dia amik dari db pass ke controller
                // kena declare kat model
                return new Admin(adminid, adminfullname, email, password);
            }
            connection.close();
        } 
        return null;
    }

    public ServiceProvider checkServiceProvider(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            // System.out.println("Email serviceprovider : "+ email );
            // System.out.println("Password serviceprovider : "+ password);
            String sql = "SELECT sid, spfullname FROM serviceprovider WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int sid = resultSet.getInt("sid");
                String spfullname = resultSet.getString("spfullname");
                return new ServiceProvider(sid, spfullname, email, password);
            }
        }
        return null;
    }
    
}