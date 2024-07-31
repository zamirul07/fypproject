package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                String spfullname = resultSet.getString("spfullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String icnumber = resultSet.getString("icnumber");
                String phonenumber = resultSet.getString("phonenumber");
                String service_name = resultSet.getString("service_name");   
                Double average_rating = resultSet.getDouble("average_rating");  
                           
                

                ServiceProvider serviceprovider = new ServiceProvider(sid, spfullname, email, password, address, icnumber, phonenumber, service_name,average_rating);
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

    public void insertRatingBooking(int bookingId, int rating) throws SQLException {
        String updateBookingSql = "UPDATE booking SET rating = ? WHERE bid = ?";
        String calculateAvgRatingSql = "SELECT sid, AVG(rating) AS avg_rating FROM booking WHERE rating IS NOT NULL GROUP BY sid";
        String updateServiceProviderSql = "UPDATE serviceprovider SET average_rating = ? WHERE sid = ?";

        try (Connection connection = dataSource.getConnection(); 
             PreparedStatement updateBookingStmt = connection.prepareStatement(updateBookingSql);
             PreparedStatement calculateAvgRatingStmt = connection.prepareStatement(calculateAvgRatingSql);
             PreparedStatement updateServiceProviderStmt = connection.prepareStatement(updateServiceProviderSql)) {

            // Update the booking rating
            updateBookingStmt.setInt(1, rating);
            updateBookingStmt.setInt(2, bookingId);
            updateBookingStmt.executeUpdate();

            // Calculate the average rating for each service provider
            ResultSet avgRatingResultSet = calculateAvgRatingStmt.executeQuery();
            while (avgRatingResultSet.next()) {
                int serviceProviderId = avgRatingResultSet.getInt("sid");
                double averageRating = avgRatingResultSet.getDouble("avg_rating");

                // Update the average rating in the serviceprovider table
                updateServiceProviderStmt.setDouble(1, averageRating);
                updateServiceProviderStmt.setInt(2, serviceProviderId);
                updateServiceProviderStmt.executeUpdate();
            }
        }
    }
    
}