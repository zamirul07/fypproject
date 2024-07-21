package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Customer;

@Repository
public class CustomerDAO {

    private final DataSource dataSource;

    @Autowired
    public CustomerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addCustomer(Customer customer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertCustomerSql = "INSERT INTO customer (customerfullname, email, password, customeraddress, customericnumber, customerphonenum ) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertCustomerSql, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, customer.getCustomerfullname());
            insertStatement.setString(2, customer.getEmail());
            insertStatement.setString(3, customer.getPassword());
            insertStatement.setString(4, customer.getCustomeraddress());
            insertStatement.setString(5, customer.getCustomericnumber());
            insertStatement.setString(6, customer.getCustomerphonenum());

            System.out.println("sapa nama ? :" + customer.getCustomerfullname());
    
            insertStatement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }
        
    }
