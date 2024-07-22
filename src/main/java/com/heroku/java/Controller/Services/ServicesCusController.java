package com.heroku.java.Controller.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroku.java.DAO.Services.ServicesCusDAO;
import com.heroku.java.Model.Services;

@Controller
public class ServicesCusController {
    private final ServicesCusDAO servicesCusDAO;

     @Autowired
    public ServicesCusController(ServicesCusDAO servicesCusDAO) {
        this.servicesCusDAO = servicesCusDAO;
    }

     @GetMapping("/viewservices")
    public String viewservices(Model model, Services services) {
        try {
            List<Services> servicesList = servicesCusDAO.viewServices();
            model.addAttribute("services", servicesList);
            return "customer/viewservices";
        } catch (SQLException e) {
            System.out.println("message : " + e.getMessage());
            return "customer/homecust";
        }
    } 
   
}
