package com.heroku.java.Model;

import java.sql.Date;

public class Booking {
    private Integer bid;
    private Integer id;
    private Integer sid;
    private Date bookingdate;
    private String bookingdesc;
    private String bookingstatus;
    private String spfullname;
    private String spaddress;
    private String spphone;
    private String service_name;
    private String customerfullname;
    private String customeraddress;

    

    public Booking(Integer bid, Date bookingdate, String bookingdesc, String bookingstatus, String customeraddress, String customerfullname, Integer id, String service_name, Integer sid, String spaddress, String spfullname, String spphone) {
        this.bid = bid;
        this.bookingdate = bookingdate;
        this.bookingdesc = bookingdesc;
        this.bookingstatus = bookingstatus;
        this.customeraddress = customeraddress;
        this.customerfullname = customerfullname;
        this.id = id;
        this.service_name = service_name;
        this.sid = sid;
        this.spaddress = spaddress;
        this.spfullname = spfullname;
        this.spphone = spphone;
    }

    

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getBookingdesc() {
        return bookingdesc;
    }

    public void setBookingdesc(String bookingdesc) {
        this.bookingdesc = bookingdesc;
    }

    public String getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(String bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    public String getSpfullname() {
        return spfullname;
    }

    public void setSpfullname(String spfullname) {
        this.spfullname = spfullname;
    }

    public String getSpaddress() {
        return spaddress;
    }

    public void setSpaddress(String spaddress) {
        this.spaddress = spaddress;
    }

    public String getSpphone() {
        return spphone;
    }

    public void setSpphone(String spphone) {
        this.spphone = spphone;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getCustomerfullname() {
        return customerfullname;
    }

    public void setCustomerfullname(String customerfullname) {
        this.customerfullname = customerfullname;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    
    

        
}