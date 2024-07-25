package com.heroku.java.Controller.Admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.Admin.ViewProfileAdminDAO;
import com.heroku.java.Model.Admin;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewProfileAdminController {
    private final ViewProfileAdminDAO viewprofileadminDAO;

    @Autowired
    public ViewProfileAdminController(ViewProfileAdminDAO viewprofileadminDAO) {
        this.viewprofileadminDAO = viewprofileadminDAO;
    }

    @GetMapping("/profileadmin")
    public String AdminProfile(@RequestParam(name = "success", required = false) Boolean success, HttpSession session, Model model) {
        Integer adminid = (Integer) session.getAttribute("adminid");
        String email = (String) session.getAttribute("email");

        System.out.println("Admin ID in session (Admin profile): " + adminid);
        System.out.println("Admin email in session (Admin profile): " + email);

        if (adminid != null && adminid != 0) {
            try {
                Admin admin = viewprofileadminDAO.AdminProfile(adminid);
                model.addAttribute("AdminProfile", admin);
            } catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                sqe.printStackTrace();
                model.addAttribute("error", "An error occurred while retrieving the admin profile. Please try again later.");
            }
        } else {
            model.addAttribute("error", "Invalid session. Please log in again.");
            return "redirect:/login";
        }

        return "admin/profileadmin";
    }
}