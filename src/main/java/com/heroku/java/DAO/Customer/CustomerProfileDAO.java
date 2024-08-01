package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Customer;

@Repository
public class CustomerProfileDAO {
    private final DataSource dataSource;

    public CustomerProfileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Customer CustomerProfile(int id) throws SQLException {
        // Your database connection and query code
        // Example implementation
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setCustomerfullname(resultSet.getString("customerfullname"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setCustomeraddress(resultSet.getString("customeraddress"));
                    customer.setCustomericnumber(resultSet.getString("customericnumber"));
                    customer.setCustomerphonenum(resultSet.getString("customerphonenum"));
                    
                    // Set other customer fields
                }
            }
        }
        return customer;
    }

    public Customer UpdateProfile (Customer customer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE customer SET customerfullname=?, email=?, password=?,  customeraddress=?, customericnumber=?, customerphonenum=? WHERE id=?";
            final var statement = connection.prepareStatement(sql);

            String password = customer.getPassword();
            System.out.println("password: " + password);
            

            statement.setString(1, customer.getCustomerfullname());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getCustomeraddress());
            statement.setString(5, customer.getCustomericnumber());
            statement.setString(6, customer.getCustomerphonenum());
            statement.setInt(7, customer.getId());

            statement.executeUpdate();
        }
        return customer;
    }

    private String password() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'customerpassword'");
    }
}