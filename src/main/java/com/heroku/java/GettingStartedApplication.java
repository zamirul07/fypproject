package com.heroku.java;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class GettingStartedApplication {
    private final DataSource dataSource;

    @Autowired
    public GettingStartedApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/homepage")
    public String homepage() {
        return "/homepage";
    }
   
    @GetMapping("/homecust")
    public String homecust() {
        return "customer/homecust"; 
    }

    @GetMapping("/homeadmin")
    public String homeadmin() {
        return "admin/homeadmin"; 
    }

    @GetMapping("/homesp")
    public String homesp() {
        return "serviceprovider/homesp"; 
    }




    @GetMapping("customer/payment")
    public String payment() {
        return "customer/payment"; 
    }
    
    @GetMapping("customer/paymenttrack")
    public String paymenttrack() {
        return "customer/paymenttrack"; 
    }

    @GetMapping("serviceprovider/viewbooking")
    public String viewbooking() {
        return "serviceprovider/viewbooking"; 
    }

    @GetMapping("serviceprovider/approvepayment")
    public String approvepayment() {
        return "serviceprovider/approvepayment"; 
    }


    @GetMapping("/database")
    String database(Map<String, Object> model) {
        try (Connection connection = dataSource.getConnection()) {
            final var statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            statement.executeUpdate("INSERT INTO ticks VALUES (now())");

            final var resultSet = statement.executeQuery("SELECT tick FROM ticks");
            final var output = new ArrayList<>();
            while (resultSet.next()) {
                output.add("Read from DB: " + resultSet.getTimestamp("tick"));
            }

            model.put("records", output);
            return "database";

        } catch (Throwable t) {
            model.put("message", t.getMessage());
            return "error";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(GettingStartedApplication.class, args);
    }
}
