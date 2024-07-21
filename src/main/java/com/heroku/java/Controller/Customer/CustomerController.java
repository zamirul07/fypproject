package com.heroku.java.Controller.Customer;
import java.sql.SQLException; 

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroku.java.DAO.Customer.CustomerDAO;
import com.heroku.java.Model.Customer;

import jakarta.servlet.http.HttpSession;





@Controller
public class CustomerController  {   
    private CustomerDAO customerDAO;
    private DataSource dataSource;

    @Autowired
    public CustomerController (CustomerDAO customerDAO){
        this.customerDAO=customerDAO;
    }

    //CREATE ACCOUNT
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // customer session
    @PostMapping("/signup")
    public String addCustomer(HttpSession session, @ModelAttribute("signup") Customer customer) {
        try {
            customerDAO.addCustomer(customer);
            return "redirect:/login";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    }

    
    // @GetMapping("/searchVolunteer")
    // public String searchVolunteer(@RequestParam("vname") String volunteerName, Model model) {
    //     try{
    //         List<Volunteer> volunteers = volunteerDAO.searchVolunteersByName(volunteerName);
    //         model.addAttribute("volunteers", volunteers); 
    //     }catch (SQLException e) {
                   
    //                 e.printStackTrace(); // You may want to log the exception instead
    //                 model.addAttribute("error", "An error occurred during the search: " + e.getMessage());
    //             }
    //     return "viewVolunteer"; // Replace with the name of your results view
    // }
    
    
