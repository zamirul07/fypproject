package com.heroku.java.Controller.Admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Admin.CreateServiceProviderDAO;
import com.heroku.java.DAO.Admin.DeleteServiceProviderDAO;
import com.heroku.java.DAO.Services.ServicesDAO;
import com.heroku.java.Model.ServiceProvider;
import com.heroku.java.Model.Services;

@Controller
public class CreateServiceProviderController {

    @Autowired
    private CreateServiceProviderDAO createserviceproviderDAO;

    @Autowired
    private DeleteServiceProviderDAO deleteserviceproviderDAO;

    @Autowired
    private ServicesDAO serviceDAO;

    @GetMapping("/createsp")
    public String showCreateServiceProviderForm(Model model) {
        try {
            List<Services> services = serviceDAO.getAllServices();
            model.addAttribute("services", services);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("addserviceProvider", new ServiceProvider());
        return "admin/createsp";
    }

    @PostMapping("/createsp")
    public String addServiceProvider(ServiceProvider serviceprovider) {
        try {
            createserviceproviderDAO.addServiceprovider(serviceprovider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/adminviewsp";
    }

    @PostMapping("/deletesp")
    public String deleteServiceProvider(@RequestParam("sid") int sid) {
        try {
            deleteserviceproviderDAO.deleteServiceProvider(sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/adminviewsp";
    }
}
