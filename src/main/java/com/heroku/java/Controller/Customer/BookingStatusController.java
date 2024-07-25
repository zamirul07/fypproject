package com.heroku.java.Controller.Customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heroku.java.DAO.Customer.BookingStatusDAO;
import com.heroku.java.Model.Booking;

@Controller
@RequestMapping("/bookingstatus")
public class BookingStatusController {

    private final BookingStatusDAO bookingStatusDAO;

    @Autowired
    public BookingStatusController(BookingStatusDAO bookingStatusDAO) {
        this.bookingStatusDAO = bookingStatusDAO;
    }

    @GetMapping
    public String bookingstatus(Model model) {
        try {
            List<Booking> bookingList = bookingStatusDAO.bookingstatus();
            model.addAttribute("bookingList", bookingList);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error"; // Return an error page if needed
        }
        return "customer/bookingstatus"; // Return the name of your booking status page
    }
}
