package com.heroku.java.Controller.Customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heroku.java.DAO.Customer.BookingStatusDAO;
import com.heroku.java.Model.Booking;
import com.heroku.java.Model.ServiceProvider;

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

    @GetMapping("/deleteBooking")
    public String deleteBooking(@RequestParam("bookingId") int bookingId, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("bid in delete=" + bookingId);
            bookingStatusDAO.deleteBookingById(bookingId);
            redirectAttributes.addFlashAttribute("successMessage", "Booking successfully deleted.");
            return "redirect:/listbooking";
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the booking.");
            e.printStackTrace();
            return "error";
        }
        // Redirect to the booking list page
    }

    @GetMapping("/recommendation")
    public String getrecommendation(@RequestParam("sid") int sid, RedirectAttributes redirectAttributes,Model model) {
        try {
            
            List <ServiceProvider> sp = bookingStatusDAO.getrecommendation(sid);
            redirectAttributes.addFlashAttribute("successMessage", "Booking successfully deleted.");
            model.addAttribute("recommendationlist", sp);
            return "customer/recommendation";
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the booking.");
            e.printStackTrace();
            return "error";
        }
    }
}
