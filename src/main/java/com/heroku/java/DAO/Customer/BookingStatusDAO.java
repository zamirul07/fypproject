package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Booking;
import com.heroku.java.Model.ServiceProvider;

@Repository
public class BookingStatusDAO {
    private final DataSource dataSource;

    public BookingStatusDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Booking> getBookingByBookingId(int bid) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        System.out.println("bid get: " + bid);
        
        String sql = "SELECT b.bid, b.id, b.sid, b.bookingdate, b.bookingdesc, b.bookingstatus, " +
                     "sp.spfullname, sp.address, sp.phonenumber, sp.service_name " +
                     "FROM booking b " +
                     "JOIN serviceprovider sp ON b.sid = sp.sid " +
                     "WHERE b.bid = ?";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, bid);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBid(resultSet.getInt("bid"));
                booking.setId(resultSet.getInt("id"));
                booking.setSid(resultSet.getInt("sid"));
                booking.setBookingdate(resultSet.getDate("bookingdate"));
                booking.setBookingdesc(resultSet.getString("bookingdesc"));
                booking.setBookingstatus(resultSet.getString("bookingstatus"));
                
                ServiceProvider serviceProvider = new ServiceProvider();
                serviceProvider.setSpfullname(resultSet.getString("spfullname"));
                serviceProvider.setAddress(resultSet.getString("address"));
                serviceProvider.setPhonenumber(resultSet.getString("phonenumber"));
                serviceProvider.setService_name(resultSet.getString("service_name"));
                
                booking.setServiceProvider(serviceProvider);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching bookings by booking ID: " + bid, e);
        }
        
        return bookings;
    }

    public List<Booking> getBookingsByCustomerId(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.bid, b.id, b.sid, b.bookingdate, b.bookingdesc, b.bookingstatus, " +
                     "sp.spfullname, sp.address, sp.phonenumber, sp.service_name " +
                     "FROM booking b " +
                     "JOIN serviceprovider sp ON b.sid = sp.sid " +
                     "WHERE b.id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBid(resultSet.getInt("bid"));
                booking.setId(resultSet.getInt("id"));
                booking.setSid(resultSet.getInt("sid"));
                booking.setBookingdate(resultSet.getDate("bookingdate"));
                booking.setBookingdesc(resultSet.getString("bookingdesc"));
                booking.setBookingstatus(resultSet.getString("bookingstatus"));

                ServiceProvider serviceProvider = new ServiceProvider();
                serviceProvider.setSpfullname(resultSet.getString("spfullname"));
                serviceProvider.setAddress(resultSet.getString("address"));
                serviceProvider.setPhonenumber(resultSet.getString("phonenumber"));
                serviceProvider.setService_name(resultSet.getString("service_name"));

                booking.setServiceProvider(serviceProvider);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching bookings by customer ID: " + customerId, e);
        }

        return bookings;
    }
}
