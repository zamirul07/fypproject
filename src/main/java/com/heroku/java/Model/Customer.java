package com.heroku.java.Model;

public class Customer {
    private int id;
    private String customerfullname;
    private String email;
    private String password;
    private String customeraddress;
    private String customericnumber;
    private String customerphonenum;

    // No-argument constructor
    public Customer() {}

    // Constructor with parameters
    public Customer(String customeraddress, String customerfullname, String customericnumber, String customerphonenum, String email, int id, String password) {
        this.customeraddress = customeraddress;
        this.customerfullname = customerfullname;
        this.customericnumber = customericnumber;
        this.customerphonenum = customerphonenum;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public Customer(int id, String customerfullname, String email, String password) {
        this.id=id;
        this.customerfullname=customerfullname;
        this.email=email;
        this.password=password;
    }

    public Customer(String customerfullname, String customeraddress, String customerphonenum){

        this.customerfullname=customerfullname;  
        this.customeraddress = customeraddress;
        this.customerphonenum = customerphonenum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerfullname() {
        return customerfullname;
    }

    public void setCustomerfullname(String customerfullname) {
        this.customerfullname = customerfullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getCustomericnumber() {
        return customericnumber;
    }

    public void setCustomericnumber(String customericnumber) {
        this.customericnumber = customericnumber;
    }

    public String getCustomerphonenum() {
        return customerphonenum;
    }

    public void setCustomerphonenum(String customerphonenum) {
        this.customerphonenum = customerphonenum;
    }



    
}