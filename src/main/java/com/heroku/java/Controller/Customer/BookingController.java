package com.heroku.java.Controller.Customer;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Customer.BookingDAO;
import com.heroku.java.Model.Booking;

import jakarta.servlet.http.HttpSession;


@Controller
public class BookingController {
    private BookingDAO bookingdao;

    @Autowired
    public BookingController(BookingDAO bookingdao){
        this.bookingdao = bookingdao;
    }


    @GetMapping("/booking")
    public String showBookingForm(@RequestParam("sid") int sid,
                                  @RequestParam("spfullname") String spfullName,
                                  @RequestParam("address") String address,
                                  @RequestParam("phonenumber") String phonenumber,
                                  @RequestParam("service_name") String service_name,
                                  Model model,HttpSession session) {
        // Add attributes to the model
        Integer id = (Integer)session.getAttribute("id");
        System.out.println("id"+id);
        model.addAttribute("sid", sid);
        model.addAttribute("id", id);
        model.addAttribute("spfullname", spfullName);
        model.addAttribute("address", address);
        model.addAttribute("phonenumber", phonenumber);
        model.addAttribute("service_name", service_name);
        
        return "customer/booking"; // Return the name of your booking details page
    }

    @PostMapping("/confirmBooking")
    public String insertBooking(Booking booking) {
        try {
            System.out.println("customerid: "+ booking.getId());
            System.out.println("service provider id : "+ booking.getSid());
            System.out.println("booking date: "+ booking.getBookingdate());
            System.out.println("booking desc: "+ booking.getBookingdesc());
            bookingdao.insertBooking(booking);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "customer/bookingstatus";
    }
}