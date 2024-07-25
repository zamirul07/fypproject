package com.heroku.java.DAO.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Booking;

@Repository
public class BookingStatusDAO {
    private final DataSource dataSource;

    public BookingStatusDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Booking> bookingstatus() throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        String sql = "SELECT b.bid, b.bookingdate, b.bookingdesc, b.bookingstatus, " +
                     "sp.spfullname, sp.address AS spaddress, sp.phonenumber AS spphone, sp.service_name, " +
                     "c.fullname AS customerfullname, c.address AS customeraddress " +
                     "FROM booking b " +
                     "JOIN serviceprovider sp ON b.sid = sp.sid " +
                     "JOIN customer c ON b.id = c.id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer bid = resultSet.getInt("bid");
                Date bookingDate = resultSet.getDate("bookingdate");
                String bookingDesc = resultSet.getString("bookingdesc");
                String bookingStatus = resultSet.getString("bookingstatus");
                String spFullname = resultSet.getString("spfullname");
                String spAddress = resultSet.getString("spaddress");
                String spPhone = resultSet.getString("spphone");
                String serviceName = resultSet.getString("service_name");
                String customerFullname = resultSet.getString("customerfullname");
                String customerAddress = resultSet.getString("customeraddress");

                Booking booking = new Booking(bid, bookingDate, bookingDesc, bookingStatus, spFullname, spAddress, spPhone, serviceName, customerFullname, customerAddress);
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            throw e;
        }
        return bookingList;
    }
}
