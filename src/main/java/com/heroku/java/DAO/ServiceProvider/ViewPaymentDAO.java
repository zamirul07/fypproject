package com.heroku.java.DAO.ServiceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.heroku.java.Model.Booking;
import com.heroku.java.Model.Customer;
import com.heroku.java.Model.ServiceProvider;

@Repository
public class ViewPaymentDAO {

    private final DataSource dataSource;

    public ViewPaymentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Booking> getBookingByServiceProviderid(int sid) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.bid, b.id, c.customerfullname, b.bookingdate, b.bookingprice, b.paymentstatus "
                + "FROM booking b "
                + "JOIN customer c ON b.id = c.id "
                + "JOIN serviceprovider sp ON b.sid = sp.sid "
                + "WHERE b.sid = ?";

        try (Connection connection = dataSource.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, sid);
            ResultSet resultSet = statement.executeQuery();
// int bid, String custName, Date bookingdate, double bookingprice, String paymentstatus, int id
            while (resultSet.next()) {
                Booking booking = new Booking(
                        resultSet.getInt("bid"),
                        resultSet.getString("customerfullname"),
                        resultSet.getDate("bookingdate"),
                        resultSet.getDouble("bookingprice"),
                        resultSet.getString("paymentstatus"),
                        resultSet.getInt("id")
                       
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching bookings for Service Provider ID: " + sid, e);
        }

        if (bookings.isEmpty()) {
            throw new SQLException("No bookings found with Service Provider ID: " + sid);
        }

        return bookings;
    }

    public Booking getBookingByBookingId(int bid, int id) throws SQLException {
        String sql = "SELECT * "
                + "FROM booking b "
                + "JOIN customer c ON b.id = c.id "
                + "JOIN serviceprovider sp ON b.sid = sp.sid "
                + "WHERE b.bid = ? AND b.id = ?";

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bid);
            statement.setInt(2, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Booking booking = new Booking();
                    booking.setBid(resultSet.getInt("bid"));
                    booking.setId(id);
                    booking.setSid(resultSet.getInt("sid"));
                    booking.setBookingdate(resultSet.getDate("bookingdate"));
                    booking.setBookingdesc(resultSet.getString("bookingdesc"));
                    booking.setBookingprice(resultSet.getDouble("bookingprice"));
                    booking.setPaymentstatus(resultSet.getString("paymentstatus"));
                    booking.setPpbyte(resultSet.getBytes("ppimage")); // Set the byte array

                    ServiceProvider serviceProvider = new ServiceProvider();
                    serviceProvider.setService_name(resultSet.getString("service_name"));
                    booking.setServiceProvider(serviceProvider); // Assuming Booking class has setServiceProvider method

                    Customer customer = new Customer();
                    customer.setCustomerfullname(resultSet.getString("customerfullname"));
                    customer.setCustomeraddress(resultSet.getString("customeraddress"));
                    customer.setCustomerphonenum(resultSet.getString("customerphonenum"));
                    booking.setCustomer(customer); // Assuming Booking class has setCustomer method

                    return booking;
                } else {
                    throw new SQLException("No booking found with Booking ID: " + bid);
                }
            }
        }
    }

    public void updatepaymentstatus(int bid, int id, int sid, String paymentstatus) throws SQLException {
        String sql = "UPDATE Booking SET paymentstatus = ? WHERE bid = ? and sid= ? and id = ?";

        try {
            Connection connection = dataSource.getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, paymentstatus);
            statement.setInt(2, bid);
            statement.setInt(3, sid);
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
