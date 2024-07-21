package com.heroku.java.Controller.Admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroku.java.DAO.Admin.ViewServiceProviderDAO;
import com.heroku.java.Model.ServiceProvider;

@Controller
public class ViewServiceProviderController {
    private final ViewServiceProviderDAO viewServiceProviderDAO;

     @Autowired
    public ViewServiceProviderController(ViewServiceProviderDAO viewServiceProviderDAO) {
        this.viewServiceProviderDAO = viewServiceProviderDAO;
    }

     @GetMapping("/adminviewsp")
    public String viewServiceProvider(Model model, ServiceProvider serviceprovider) {
        try {
            List<ServiceProvider> serviceproviderList = viewServiceProviderDAO.viewServiceProvider();
            model.addAttribute("serviceprovider", serviceproviderList);
            return "admin/adminviewsp";
        } catch (SQLException e) {
            System.out.println("message : " + e.getMessage());
            return "admin/homeadmin";
        }
    } 

    
}