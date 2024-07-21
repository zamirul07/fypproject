package com.heroku.java.Controller.Customer;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Customer.CustomerProfileDAO;
import com.heroku.java.Model.Customer;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerProfileController {
    private final CustomerProfileDAO customerProfileDAO;

    @Autowired
    public CustomerProfileController(CustomerProfileDAO customerProfileDAO) {
        this.customerProfileDAO = customerProfileDAO;
    }

    @GetMapping("/profilecustomer")
    public String customerProfile(@RequestParam(name = "success", required = false) Boolean success, HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("id");
        String email = (String) session.getAttribute("email");

        System.out.println("Customer ID in session (Customer profile): " + id);
        System.out.println("Customer email in session (Customer profile): " + email);

        if (id != null && id != 0) {
            try {
                Customer customer = customerProfileDAO.CustomerProfile(id);
                model.addAttribute("CustomerProfile", customer);
            } catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                sqe.printStackTrace();
                model.addAttribute("error", "An error occurred while retrieving the customer profile. Please try again later.");
            }
        } else {
            model.addAttribute("error", "Invalid session. Please log in again.");
            return "redirect:/login";
        }

        return "customer/profilecustomer";
    }
    
     @PostMapping("/UpdateProfile")
    public String updateProfile(HttpSession session, @ModelAttribute("CustomerProfile") Customer customer, Model model) {
       
        int id=customer.getId();

        String customerfullname = customer.getCustomerfullname();
       
        System.out.println("customer id in session (customer update): " + id);
        System.out.println("customer name in session (customer update): " + customerfullname);
        

        if (id != 0) {
            try {
                
                customer = customerProfileDAO.UpdateProfile(customer);
                session.setAttribute("CustomerProfile", customer);
                return "redirect:/profilecustomer?profileSuccess=true";
            } 
            
            catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                System.out.println("printTrace /n");
                sqe.printStackTrace();
                return "redirect:/homecust";
            }
        }
        return "redirect:customer/profilecustomer?profileSuccess=true";
    }

    
}

