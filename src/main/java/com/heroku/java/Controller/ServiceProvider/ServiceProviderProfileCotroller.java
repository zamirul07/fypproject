package com.heroku.java.Controller.ServiceProvider;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.DAO.ServiceProvider.ServiceProviderProfileDAO;
import com.heroku.java.Model.ServiceProvider;

import jakarta.servlet.http.HttpSession;

@Controller
public class ServiceProviderProfileCotroller {

    private final ServiceProviderProfileDAO serviceproviderProfileDAO;

    @Autowired
    public ServiceProviderProfileCotroller(ServiceProviderProfileDAO serviceproviderProfileDAO) {
        this.serviceproviderProfileDAO = serviceproviderProfileDAO;
    }

    @GetMapping("/profileserviceprovider")
    public String ServiceProviderProfile(@RequestParam(name = "success", required = false) Boolean success, HttpSession session, Model model) {
        Integer sid = (Integer) session.getAttribute("sid");
        String email = (String) session.getAttribute("email");

        System.out.println("serviceprovider ID in session (serviceprovider profile): " + sid);
        System.out.println("serviceprovider email in session (serviceprovider profile): " + email);

        if (sid != null && sid != 0) {
            try {
                ServiceProvider serviceprovider = serviceproviderProfileDAO.ServiceProviderProfile(sid);
                model.addAttribute("ServiceProviderProfile", serviceprovider);
            } catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                sqe.printStackTrace();
                model.addAttribute("error", "An error occurred while retrieving the serviceprovider profile. Please try again later.");
            }
        } else {
            model.addAttribute("error", "Invalid session. Please log in again.");
            return "redirect:/login";
        }

        return "ServiceProvider/profileserviceprovider";
    }

    @PostMapping("/updateProfile")
    public String UpdateProfile(HttpSession session, @ModelAttribute("ServiceProviderProfile") ServiceProvider serviceprovider, Model model) {
        int sid = serviceprovider.getSid();
        String spfullname = serviceprovider.getSpfullname();

        System.out.println("serviceprovider id in session (serviceprovider update): " + sid);
        System.out.println("serviceprovider name in session (serviceprovider update): " + spfullname);

        if (sid != 0) {
            try {
                serviceprovider = serviceproviderProfileDAO.updateProfile(serviceprovider);
                session.setAttribute("profileserviceprovider", serviceprovider);
                return "redirect:/profileserviceprovider?profileSuccess=true";
            } catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                sqe.printStackTrace();
                return "redirect:/homesp";
            }
        }
        return "redirect:/profileserviceprovider?profileSuccess=true";
    }

    @PostMapping("/deleteProfile")
    public String deleteProfile(HttpSession session, @ModelAttribute("ServiceProviderProfile") ServiceProvider serviceprovider, Model model) {
        int sid = serviceprovider.getSid();

        System.out.println("serviceprovider id in session (serviceprovider delete): " + sid);

        if (sid != 0) {
            try {
                serviceproviderProfileDAO.deleteProfile(sid);
                session.invalidate(); // Invalidate the session after deleting the profile
                return "redirect:/login?profileDeleted=true"; // Redirect to login page after deletion
            } catch (SQLException sqe) {
                System.out.println("Error Code = " + sqe.getErrorCode());
                System.out.println("SQL state = " + sqe.getSQLState());
                System.out.println("Message = " + sqe.getMessage());
                sqe.printStackTrace();
                return "redirect:/profileserviceprovider?error=deletionFailed";
            }
        }
        return "redirect:/profileserviceprovider?error=invalidSession";
    }
}
