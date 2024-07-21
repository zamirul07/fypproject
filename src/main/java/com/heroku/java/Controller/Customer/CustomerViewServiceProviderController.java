package com.heroku.java.Controller.Customer;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroku.java.DAO.Customer.CustomerViewServiceProviderDAO;
import com.heroku.java.Model.ServiceProvider;

@Controller
public class CustomerViewServiceProviderController {
    private final CustomerViewServiceProviderDAO customerviewServiceProviderDAO;

     @Autowired
    public CustomerViewServiceProviderController(CustomerViewServiceProviderDAO customerviewServiceProviderDAO) {
        this.customerviewServiceProviderDAO = customerviewServiceProviderDAO;
    }

     @GetMapping("/viewsp")
    public String customerviewServiceProvider(Model model, ServiceProvider serviceprovider) {
        try {
            List<ServiceProvider> serviceproviderList = customerviewServiceProviderDAO.customerviewServiceProvider();
            model.addAttribute("serviceprovider", serviceproviderList);
            return "customer/viewsp";
        } catch (SQLException e) {
            System.out.println("message : " + e.getMessage());
            return "customer/homecust";
        }
    } 

    
}