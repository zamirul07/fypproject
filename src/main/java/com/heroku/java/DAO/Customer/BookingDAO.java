package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Booking;
import com.heroku.java.Model.ServiceProvider;

@Repository
public class BookingDAO {
    private final DataSource dataSource;

    public BookingDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

        public Booking insertBooking(Booking booking) throws SQLException {
            String sql = "INSERT INTO booking (sid, id, bookingdate, bookingdesc, bookingstatus) VALUES (?, ?, ?, ?, ?) RETURNING bid";
            
            try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                    int bid = 0;
                statement.setInt(1, booking.getSid());
                statement.setInt(2, booking.getId());
                statement.setDate(3, booking.getBookingdate());
                statement.setString(4, booking.getBookingdesc());
                statement.setString(5, "pending");
                try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                bid = rs.getInt("bid");
                booking.setBid(bid); 
                System.out.println("bid from dao: "+ bid);
                }
            }
            } catch (SQLException e) {
                throw e;
            }
            return booking;
        }

        public List<Booking> getBookingByBookingId(int bid) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        
        System.out.println("bid get: " + bid);
        
        String sql = "SELECT b.bid, b.id, b.sid, b.bookingdate, b.bookingdesc, b.bookingstatus, b.bookingprice, b.paymentstatus, " +
                     "sp.spfullname, sp.address, sp.phonenumber, sp.service_name, " +
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
                booking.setBookingprice(resultSet.getDouble("bookingprice"));
                booking.setBookingstatus(resultSet.getString("paymentstatus"));
                
                
                ServiceProvider serviceProvider = new ServiceProvider();
                serviceProvider.setSpfullname(resultSet.getString("spfullname"));
                serviceProvider.setAddress(resultSet.getString("address"));
                serviceProvider.setPhonenumber(resultSet.getString("phonenumber"));
                serviceProvider.setService_name(resultSet.getString("service_name"));
                
              
                byte[] qrcodeBytes = resultSet.getBytes("qrcode");
                String base64Image = Base64.getEncoder().encodeToString(qrcodeBytes);
                String imageSrc = "data:image/jpeg;base64," + base64Image;
                serviceProvider.setQrcodemage(imageSrc);

                booking.setServiceProvider(serviceProvider);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching bookings by booking ID: " + bid, e);
        }
        
        return bookings;
    }

    
}
