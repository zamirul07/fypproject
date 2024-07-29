package com.heroku.java.Controller.ServiceProvider;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heroku.java.DAO.ServiceProvider.ViewCustomerBoookingDAO;
import com.heroku.java.Model.Booking;

import jakarta.servlet.http.HttpSession;



@Controller
public class ViewCustomerBookingController {
    

    private final ViewCustomerBoookingDAO viewCustomerBoookingDAO;

    @Autowired
    public ViewCustomerBookingController(ViewCustomerBoookingDAO viewCustomerBoookingDAO) {
        this.viewCustomerBoookingDAO = viewCustomerBoookingDAO;
    }

    @GetMapping("/viewbooking")
    public String getServiceProviderBookings(HttpSession session, Model model) {
        Integer Sid = (Integer) session.getAttribute("sid");
        if (Sid == null) {
            return "redirect:/login"; // Redirect to login if the customer is not logged in
        }
        try {
            List<Booking> bookingList = viewCustomerBoookingDAO.getBookingsByServiceProviderId(Sid);
            for (Booking booking : bookingList) {
                if (booking.getBookingprice() == null) {
                    booking.setBookingprice(0.00);
                }
            }
            model.addAttribute("bookingList", bookingList);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error fetching booking details.");
        }
        return "serviceprovider/viewbooking";
    }  

    @GetMapping("/accept")
    public String confirmationPage(@RequestParam("bookingid") int bookingId, Model model,HttpSession session) {
        Integer sid = (Integer) session.getAttribute("sid");
        try {
            Booking booking = viewCustomerBoookingDAO.getBookingByIdAndSID(bookingId,sid);
            model.addAttribute("booking", booking);
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Error fetching booking details for ID: " + bookingId);
        }
        return "serviceprovider/confirmation"; // Ensure this matches the Thymeleaf template location
    }
    

    @PostMapping("/serviceprovider/confirm")
    public String postMethodName(Model model, Booking booking, HttpSession session) {
       System.out.println("booking.bid"+booking.getBid());
       System.out.println("booking.bookingprice"+booking.getBookingprice());
       int bookingId = booking.getBid();
       double price = booking.getBookingprice();
       Integer sid = (Integer) session.getAttribute("sid");
       try {
        viewCustomerBoookingDAO.updatePriceBooking(bookingId,price,sid);
       } catch (SQLException e) {
        e.printStackTrace();
       }
       
        return "redirect:/viewbooking";
    }
    
     @PostMapping("/reject")
    public String rejectBooking(@RequestParam("bookingid") int bookingId, RedirectAttributes redirectAttributes) {
        try {
            viewCustomerBoookingDAO.deleteBookingById(bookingId);
            redirectAttributes.addFlashAttribute("successMessage", "Booking successfully deleted.");
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the booking.");
            e.printStackTrace();
        }
        return "redirect:/viewbooking"; // Redirect to the booking list page
    }

    

    

}


