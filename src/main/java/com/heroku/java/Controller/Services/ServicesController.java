package com.heroku.java.Controller.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroku.java.DAO.Services.ServicesDAO;
import com.heroku.java.Model.Services;

@Controller
public class ServicesController {
    private final ServicesDAO servicesDAO;

     @Autowired
    public ServicesController(ServicesDAO servicesDAO) {
        this.servicesDAO = servicesDAO;
    }

     @GetMapping("/adminviewservices")
    public String viewservices(Model model, Services services) {
        try {
            List<Services> servicesList = servicesDAO.viewServices();
            model.addAttribute("services", servicesList);
            return "admin/adminviewservices";
        } catch (SQLException e) {
            System.out.println("message : " + e.getMessage());
            return "admin/homeadmin";
        }
    }
   
}
