package com.heroku.java.Model;

import java.sql.Date;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class Booking {
    private Integer bid;
    private Integer id;
    private Integer sid;
    private Date bookingdate;
    private String bookingdesc;
    private String bookingstatus;
    private ServiceProvider serviceprovider;
    private Customer customer;
    private Double bookingprice;
   private byte[] ppbyte;
    public MultipartFile ppimage;
    String imageSrc;
    private String paymentstatus;


    

    public Booking(Integer bid, Integer id, Integer sid,  Date bookingdate, String bookingdesc, String bookingstatus) {
        this.bid = bid;
        this.id = id;
        this.sid = sid;
        this.bookingdate = bookingdate;
        this.bookingdesc = bookingdesc;
        this.bookingstatus = bookingstatus;   
         
        
    }

    public Booking(Integer bid, Integer id, Integer sid, Date bookingdate, String bookingdesc, String bookingstatus, ServiceProvider serviceprovider, Customer customer,  byte[] ppbyte, MultipartFile ppimage, String imageSrc, String paymentstatus) {
        this.bid = bid;
        this.id = id;
        this.sid = sid;
        this.bookingdate = bookingdate;
        this.bookingdesc = bookingdesc;
        this.bookingstatus = bookingstatus;
        this.serviceprovider = serviceprovider;
        this.customer = customer;
        this.ppbyte = ppbyte;
        this.ppimage = ppimage;
        this.imageSrc = imageSrc;
        this.paymentstatus = paymentstatus;

    }

     

    public Booking() {
    }
    
    public String getBase64Image() {
        if (this.ppbyte != null) {
            return Base64.getEncoder().encodeToString(this.ppbyte);
        }
        return null;
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

    public ServiceProvider getServiceProvider() {
        return serviceprovider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceprovider = serviceProvider;
    } 

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    } 

    public Double getBookingprice() {
        return bookingprice;
    }

    public void setBookingprice(double bookingprice) {
        this.bookingprice = bookingprice;
    }   

    public byte[] getPpbyte() {
        return this.ppbyte;
    }

    public void setPpbyte(byte[] ppbyte) {
        this.ppbyte = ppbyte;
    }

    public MultipartFile getPpimage() {
        return this.ppimage;
    }

    public void setPpimage(MultipartFile ppimage) {
        this.ppimage = ppimage;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getPaymentstatus() {
        return this.paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    
}