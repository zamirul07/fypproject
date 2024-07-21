package com.heroku.java.Controller.Admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Admin.CreateServicesDAO;
import com.heroku.java.Model.Services;


@Controller
public class CreateServicesController {
    private final CreateServicesDAO createServicesDAO;

    @Autowired
    public CreateServicesController(CreateServicesDAO createServicesDAO) {
        this.createServicesDAO = createServicesDAO;
    }

    @GetMapping("/createservices")
    public String createservices() {
        return "admin/createservices";
    }

    @PostMapping("/createservices")
    public String createServices(@ModelAttribute Services services, Model model) {
        try {
            createServicesDAO.addServices(services);
            return "redirect:/adminviewservices"; // Redirect to prevent form resubmission
        } catch (SQLException e) {
            e.printStackTrace();
            return "admin/homeadmin"; // Return to form with error message
        }
    }

    @PostMapping("/deleteservice")
    public String deleteService(@RequestParam("service_id") int service_id, Model model) {
        try {
            createServicesDAO.deleteServices(service_id);
            return "redirect:/adminviewservices"; // Redirect to refresh the service list
        } catch (SQLException e) {
            System.out.println("message : " + e.getMessage());
            model.addAttribute("error", "Failed to delete service: " + e.getMessage());
            return "admin/adminviewsp";
        }
    }

    @PostMapping("/updateservices")
    public String updateService(@RequestParam("service_id") int service_id,
                                @RequestParam("service_name") String service_name,
                                @RequestParam("service_desc") String service_desc,
                                Model model) {
        try {
            createServicesDAO.updateServices(service_id, service_name, service_desc);
            model.addAttribute("message", "Service updated successfully!");
        } catch (SQLException e) {
            model.addAttribute("error", "Error updating service: " + e.getMessage());
        }
        return "redirect:/adminviewservices";
    }
}
