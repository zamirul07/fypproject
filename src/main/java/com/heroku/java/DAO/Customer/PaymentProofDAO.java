package com.heroku.java.DAO.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Booking;
import com.heroku.java.Model.ServiceProvider;
@Repository
public class PaymentProofDAO {
    private final DataSource dataSource;
    public PaymentProofDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addPaymentProof(Booking booking) throws SQLException {
        String sql = "UPDATE booking SET ppimage = ?, paymentstatus = 'pending' WHERE bid = ?";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setBytes(1, booking.getPpbyte());
            statement.setInt(2, booking.getBid());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating payment proof for booking ID: " + booking.getBid(), e);
        }
    }

    

    public Booking getBookingDetails(int bid) throws SQLException {
        Booking booking = null;

        String sql = "SELECT b.bid, b.id, b.sid, b.bookingdate, b.bookingdesc, b.bookingstatus, b.bookingprice, b.ppimage, sp.qrcode, " +
                     "sp.spfullname, sp.address, sp.phonenumber, sp.service_name, b.paymentstatus " +
                     "FROM booking b " +
                     "JOIN serviceprovider sp ON b.sid = sp.sid " +
                     "WHERE b.bid = ?";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, bid);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                booking = new Booking();
                booking.setBid(resultSet.getInt("bid"));
                booking.setId(resultSet.getInt("id"));
                booking.setSid(resultSet.getInt("sid"));
                booking.setBookingdate(resultSet.getDate("bookingdate"));
                booking.setBookingdesc(resultSet.getString("bookingdesc"));
                booking.setBookingstatus(resultSet.getString("bookingstatus"));
                booking.setBookingprice(resultSet.getDouble("bookingprice"));
                booking.setPaymentstatus(resultSet.getString("paymentstatus"));
                booking.setPpbyte(resultSet.getBytes("ppimage")); // Set the byte array
    
                ServiceProvider serviceProvider = new ServiceProvider();
                serviceProvider.setSpfullname(resultSet.getString("spfullname"));
                serviceProvider.setAddress(resultSet.getString("address"));
                serviceProvider.setPhonenumber(resultSet.getString("phonenumber"));
                serviceProvider.setService_name(resultSet.getString("service_name"));
    
                booking.setServiceProvider(serviceProvider);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching booking details by booking ID: " + bid, e);
        }
        
        return booking;
    }
    
    
}