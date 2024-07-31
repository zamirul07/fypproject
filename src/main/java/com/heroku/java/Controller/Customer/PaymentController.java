package com.heroku.java.Controller.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.heroku.java.DAO.Customer.BookingDAO;
import com.heroku.java.DAO.Customer.BookingStatusDAO;
import com.heroku.java.DAO.Customer.PaymentProofDAO;
import com.heroku.java.Model.Booking;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {

    @Autowired
    private PaymentProofDAO paymentProofDAO;

    @Autowired
    private BookingStatusDAO bookingStatusDAO;

    @Autowired
    private BookingDAO bookingDAO;

    @GetMapping("/payment")
    public String getPaymentDetails(@RequestParam("bookingid") int bookingid, Model model) {
        try {
            System.out.println("bid payment: " + bookingid);
            List<Booking> bookings = bookingStatusDAO.getBookingByBookingId(bookingid);
            if (bookings != null && !bookings.isEmpty()) {
                model.addAttribute("booking", bookings.get(0));
            } else {
                model.addAttribute("errorMessage", "Booking not found");
            }
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "An error occurred while retrieving the booking details");
        }
        return "customer/payment";
    }

    // @PostMapping("/UploadPaymentProof")
    // public String uploadPaymentProof(HttpSession session, 
    //                                  @RequestParam("bookingId") int bookingId,
    //                                  @RequestParam("ppimage") MultipartFile ppimage, 
    //                                  Booking booking) {

    //     Integer id = (Integer) session.getAttribute("id");

    //     if (id == null) {
    //         return "redirect:/login";
    //     }

    //     try {
    //         booking.setPpbyte(ppimage.getBytes());
    //         booking.setId(id);

    //         paymentProofDAO.addPaymentProof(booking);

    //         return "redirect:/paymenttrack?bookingId=" + bookingId;

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         // Log the error and redirect to an error page
    //         return "redirect:/error?message=Failed to process the uploaded file";
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         // Log the error and redirect to an error page
    //         return "redirect:/error?message=Database error occurred";
    //     }
    // }

    @PostMapping("/UploadPaymentProof")
public String uploadPaymentProof(HttpSession session, 
                                 @RequestParam("bid") int bookingId,
                                 @RequestParam("ppimage") MultipartFile ppimage, 
                                 Booking booking) {

    Integer id = (Integer) session.getAttribute("id");

    if (id == null) {
        return "redirect:/login";
    }

    try {
        booking.setPpbyte(ppimage.getBytes()); // Set the byte array
        booking.setId(id);

        paymentProofDAO.addPaymentProof(booking);

        return "redirect:/paymenttrack?bookingId=" + bookingId;

    } catch (IOException e) {
        e.printStackTrace();
        // Log the error and redirect to an error page
        return "redirect:/error?message=Failed to process the uploaded file";
    } catch (SQLException e) {
        e.printStackTrace();
        // Log the error and redirect to an error page
        return "redirect:/error?message=Database error occurred";
    }
}


    @GetMapping("/paymenttrack")
    public String getBookingDetails(@RequestParam("bid") int bid, Model model) {
        try {
            System.out.println("track: " + bid);
            Booking booking = paymentProofDAO.getBookingDetails(bid);

            if (booking != null) {
                model.addAttribute("booking", booking);
                model.addAttribute("bid", bid);
            } else {
                model.addAttribute("errorMessage", "No booking found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while retrieving the booking details.");
        }
        return "customer/paymenttrack";
    }

}
