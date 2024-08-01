package com.heroku.java.Controller.Customer;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Customer.CustomerViewServiceProviderDAO;
import com.heroku.java.DAO.Services.ServicesDAO;
import com.heroku.java.Model.ServiceProvider;
import com.heroku.java.Model.Services;

@Controller
public class CustomerViewServiceProviderController {
    private final CustomerViewServiceProviderDAO customerviewServiceProviderDAO;

     @Autowired
    public CustomerViewServiceProviderController(CustomerViewServiceProviderDAO customerviewServiceProviderDAO) {
        this.customerviewServiceProviderDAO = customerviewServiceProviderDAO;
    }

     @Autowired
    private ServicesDAO serviceDAO;

     @GetMapping("/viewsp")
    public String customerviewServiceProvider(Model model, ServiceProvider serviceprovider) {
        try {
            List<ServiceProvider> serviceproviderList = customerviewServiceProviderDAO.customerviewServiceProvider();
            model.addAttribute("serviceprovider", serviceproviderList);
            List<Services> services = serviceDAO.getAllServices();
            model.addAttribute("services", services);
            return "customer/viewsp";
        } catch (SQLException e) {
            System.out.println("message : " + e.getMessage());
            return "customer/homecust";
        }

    }

    // @PostMapping("/rating")
    // public String submitRating(
    //     @RequestParam("bid") int bookingId,
    //     @RequestParam("rating") int rating,

    //     Model model) {
    //     try {
    //         customerviewServiceProviderDAO.insertRatingBooking(bookingId, rating);
    //         //count rating from 
    //         model.addAttribute("message", "Rating submitted successfully");
    //     } catch (SQLException e) {
    //         model.addAttribute("error", "Error submitting rating: " + e.getMessage());
    //     }
    //     return "redirect:/viewsp";
    // }
    @PostMapping("/rating")
public String submitRating(
    @RequestParam("bid") int bookingId,
    @RequestParam("rating") int rating,
    @RequestParam("rec") String rec,
    @RequestParam("sid") int sid,
    Model model) {
    try {

        System.out.println("recommendation" + rec);
        customerviewServiceProviderDAO.insertRatingBooking(bookingId, rating, rec, sid);
        model.addAttribute("message", "Rating submitted successfully");
    } catch (SQLException e) {
        model.addAttribute("error", "Error submitting rating: " + e.getMessage());
    }
    return "redirect:/viewsp";
}
}