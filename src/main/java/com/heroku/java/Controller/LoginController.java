package com.heroku.java.Controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.LoginDAO;
import com.heroku.java.Model.Admin;
import com.heroku.java.Model.Customer;
import com.heroku.java.Model.ServiceProvider;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final LoginDAO loginDAO;

    @Autowired
    public LoginController(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("email") String email,
                        @RequestParam("password") String password, Model model) {
        try {
            // Check login credentials
            Customer isCustomer = loginDAO.checkCustomer(email, password);
            Admin isAdmin = loginDAO.checkAdmin(email, password);
            ServiceProvider isServiceProvider = loginDAO.checkServiceProvider(email, password);

            // Setup session for customer
            if (isCustomer != null) {
                session.setAttribute("email", isCustomer.getEmail());
                session.setAttribute("id", isCustomer.getId());

                // Debug
                System.out.println("Customer email who login: " + email);
                System.out.println("Customer ID who login: " + isCustomer.getId());
                return "redirect:/homecust"; // Replace with the appropriate customer home page URL
            }

            // Setup session for admin
            else if (isAdmin != null) {
                session.setAttribute("email", isAdmin.getemail());
                session.setAttribute("adminid", isAdmin.getAdminid());
                

                // Debug
                System.out.println("Admin email who login: " + isAdmin.getemail());
                System.out.println("Admin ID who login: " + isAdmin.getAdminid());
              

                return "redirect:/homeadmin"; // Replace with the appropriate admin home page URL
            } 
            
             // Setup session for Service Provider
            else if (isServiceProvider != null) {
                session.setAttribute("email", isServiceProvider.getEmail());
                session.setAttribute("sid", isServiceProvider.getSid());
                

                // Debug
                System.out.println("Service Provider email who login: " + isServiceProvider.getEmail());
                System.out.println("Service Provider ID who login: " + isServiceProvider.getSid());
              

                return "redirect:/homesp"; // Replace with the appropriate sp home page URL
            }
            
            else {
                System.out.println("Invalid username or password");
                model.addAttribute("error", true);
                return "login";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
