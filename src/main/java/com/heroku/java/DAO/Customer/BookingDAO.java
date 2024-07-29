package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Booking;

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

    
}
