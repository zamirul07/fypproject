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
public class ViewCustomerBoookingDAO {

    private final DataSource dataSource;

    public ViewCustomerBoookingDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Booking> getBookingsByServiceProviderId(int sid) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.bid, b.id, b.sid, b.bookingdate, b.bookingdesc, b.bookingstatus,b.bookingprice," +
                     "c.customerfullname, c.customeraddress, c.customerphonenum, " +
                     "sp.spfullname, sp.address, sp.phonenumber, sp.service_name " +
                     "FROM booking b " +
                     "JOIN customer c ON b.id = c.id " +
                     "JOIN serviceprovider sp ON b.sid = sp.sid " +
                     "WHERE b.sid = ?";

        try (Connection connection = dataSource.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, sid);
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

                Customer customer = new Customer();
                customer.setCustomerfullname(resultSet.getString("customerfullname"));
                customer.setCustomeraddress(resultSet.getString("customeraddress"));
                customer.setCustomerphonenum(resultSet.getString("customerphonenum"));

                ServiceProvider serviceProvider = new ServiceProvider();
                serviceProvider.setSpfullname(resultSet.getString("spfullname"));
                serviceProvider.setAddress(resultSet.getString("address"));
                serviceProvider.setPhonenumber(resultSet.getString("phonenumber"));
                serviceProvider.setService_name(resultSet.getString("service_name"));

                booking.setCustomer(customer);
                booking.setServiceProvider(serviceProvider);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching bookings by service provider ID: " + sid, e);
        }

        return bookings;
    }

    public Booking getBookingById(int bookingId) throws SQLException {
        String sql = "SELECT b.bid, b.id, b.sid, b.bookingdate, b.bookingdesc, b.bookingstatus, " +
                     "c.customerfullname, c.customeraddress, c.customerphonenum, " +
                     "sp.spfullname, sp.address, sp.phonenumber, sp.service_name " +
                     "FROM booking b " +
                     "JOIN customer c ON b.id = c.id " +
                     "JOIN serviceprovider sp ON b.sid = sp.sid " +
                     "WHERE b.bid = ?";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBid(resultSet.getInt("bid"));
                booking.setId(resultSet.getInt("id"));
                booking.setSid(resultSet.getInt("sid"));
                booking.setBookingdate(resultSet.getDate("bookingdate"));
                booking.setBookingdesc(resultSet.getString("bookingdesc"));
                booking.setBookingstatus(resultSet.getString("bookingstatus"));
                
    
                Customer customer = new Customer();
                customer.setCustomerfullname(resultSet.getString("customerfullname"));
                customer.setCustomeraddress(resultSet.getString("customeraddress"));
                customer.setCustomerphonenum(resultSet.getString("customerphonenum"));
    
                ServiceProvider serviceProvider = new ServiceProvider();
                serviceProvider.setSpfullname(resultSet.getString("spfullname"));
                serviceProvider.setAddress(resultSet.getString("address"));
                serviceProvider.setPhonenumber(resultSet.getString("phonenumber"));
                serviceProvider.setService_name(resultSet.getString("service_name"));
    
                booking.setCustomer(customer);
                booking.setServiceProvider(serviceProvider);
    
                return booking;
            } else {
                throw new SQLException("No booking found with ID: " + bookingId);
            }
        }
    }

    public Booking getBookingByIdAndSID(int bookingId, int sid) throws SQLException {
        String sql = "SELECT * FROM booking b " +
                     "JOIN serviceprovider sp " +
                     "ON b.sid = sp.sid " +
                     "WHERE b.bid = ? AND b.sid = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, bookingId);
            preparedStatement.setInt(2, sid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Booking booking = new Booking();
                    booking.setBid(resultSet.getInt("bid"));
                    booking.setSid(resultSet.getInt("sid"));
                    booking.setBookingdesc(resultSet.getString("bookingdesc"));
                    booking.setBookingdate(resultSet.getDate("bookingdate"));
                    // Set other Booking attributes as needed

                    ServiceProvider serviceProvider = new ServiceProvider();
                    serviceProvider.setSid(resultSet.getInt("sid"));
                    serviceProvider.setService_name(resultSet.getString("service_name"));
                    // Set other ServiceProvider attributes as needed

                    booking.setServiceProvider(serviceProvider);

                    return booking;
                } else {
                    return null; // Booking not found
                }
            }
        }
    }

    public void updatePriceBooking(int bookingId, double price, int sid) throws SQLException {
        String sql = "UPDATE booking SET bookingprice = ? WHERE bid = ? and sid=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, bookingId);
            preparedStatement.setInt(3, sid);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteBookingById(int bookingId) throws SQLException {
        String sql = "DELETE FROM booking WHERE bid = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        }
    }

}
