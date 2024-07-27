package com.heroku.java.Controller.Customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroku.java.DAO.Customer.BookingStatusDAO;
import com.heroku.java.Model.Booking;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewBookingController {

    private final BookingStatusDAO bookingStatusDAO;

    @Autowired
    public ViewBookingController(BookingStatusDAO bookingStatusDAO) {
        this.bookingStatusDAO = bookingStatusDAO;
    }

    @GetMapping("/listbooking")
    public String getCustomerBookings(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("id");
        if (customerId == null) {
            return "redirect:/login"; // Redirect to login if the customer is not logged in
        }
        try {
            List<Booking> bookingList = bookingStatusDAO.getBookingsByCustomerId(customerId);
            model.addAttribute("bookingList", bookingList);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error fetching booking details.");
        }
        return "customer/listbooking";
    }
}
