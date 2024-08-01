package com.heroku.java.Controller.ServiceProvider;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.ServiceProvider.ViewPaymentDAO;
import com.heroku.java.Model.Booking;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewPaymentController {

    private final ViewPaymentDAO viewPaymentDAO;

    @Autowired
    public ViewPaymentController(ViewPaymentDAO viewPaymentDAO) {
        this.viewPaymentDAO = viewPaymentDAO;
    }

    @GetMapping("/viewpayment")//edit kt sini babi
    public String getBookingByServiceProviderid(HttpSession session, Model model) {
        Integer sid = (Integer) session.getAttribute("sid");
        try {
             List<Booking> bookingList = viewPaymentDAO.getBookingByServiceProviderid(sid);
            System.out.println("sid view payment:" + sid);
            if (bookingList != null) {
                model.addAttribute("bookingList", bookingList);
                model.addAttribute("sid", sid);
            } else {
                model.addAttribute("errorMessage", "No booking found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while retrieving the booking details.");
        }
        return "ServiceProvider/viewpayment";
    }

    @GetMapping("/approvepayment")
    public String approvePayment(@RequestParam("bid") int bid, @RequestParam("id") int id, Model model) {
        try {
            // Fetch booking details by booking ID
            Booking booking = viewPaymentDAO.getBookingByBookingId(bid, id);
            if (booking != null) {
                model.addAttribute("booking", booking);
            } else {
                model.addAttribute("errorMessage", "No booking found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while retrieving the booking details.");
        }
        return "serviceprovider/approvepayment";
    }

    @PostMapping("/approvePayment")
    public String approvePayment(
            @RequestParam("bid") int bid, 
            @RequestParam("id") int id, 
            @RequestParam("sid") int sid,
            @RequestParam("action") String action,
            Model model) {
    
        String paymentStatus;
    
        if ("approve".equals(action)) {
            paymentStatus = "Paid";
        } else if ("reject".equals(action)) {
            paymentStatus = "Rejected";
        } else {
            model.addAttribute("error", "Invalid action");
            return "errorPage"; // Replace with your error page URL
        }
    
        try {
            viewPaymentDAO.updatepaymentstatus(bid, id, sid, paymentStatus);
            System.out.println("Payment status updated for bid: " + bid);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database update failed");
            return "errorPage"; // Replace with your error page URL
        }
    
        return "redirect:/viewpayment";  // Replace with your payment page URL
    }
    
}