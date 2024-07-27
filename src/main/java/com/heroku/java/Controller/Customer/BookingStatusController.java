package com.heroku.java.Controller.Customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Customer.BookingStatusDAO;
import com.heroku.java.Model.Booking;

@Controller
public class BookingStatusController {

    @Autowired
    private BookingStatusDAO bookingStatusDAO;


    @GetMapping("/bookingstatus")
    public String getBookingStatus(@RequestParam("bid") int bid, @RequestParam("sid") int sid, Model model) {
        try {
            System.out.println("bid get controller: "+ bid);
            List<Booking> bookingList = bookingStatusDAO.getBookingByBookingId(bid);
            model.addAttribute("bookingList", bookingList);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error fetching booking details.");
        }
        return "customer/bookingstatus";
    }
}
