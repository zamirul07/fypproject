package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void insertBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO booking (sid, id, bookingdate, bookingdesc, bookingstatus) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, booking.getSid());
            statement.setInt(2, booking.getId());
            statement.setDate(3, booking.getBookingdate());
            statement.setString(4, booking.getBookingdesc());
            statement.setString(5, "pending");

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
