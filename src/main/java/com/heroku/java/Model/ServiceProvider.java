package com.heroku.java.Model;

import org.springframework.web.multipart.MultipartFile;

public class ServiceProvider {

    private Integer sid;
    private String spfullname;
    private String email;
    private String password;
    private String address;
    private String icnumber;
    private String phonenumber;
    private String service_name;
    private byte[] qrcodemg;
    public MultipartFile qrcodemgs;
    String qrcodemage;
    private Double average_rating;

    public ServiceProvider() {
    }

    public ServiceProvider(Integer sid, String spfullname, String email, String password, String address, String icnumber, String phonenumber, String service_name, byte[] qrcodemg, MultipartFile qrcodemgs, String qrcodemage) {
        this.sid = sid;
        this.spfullname = spfullname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.service_name = service_name;
        this.qrcodemg = qrcodemg;
        this.qrcodemgs = qrcodemgs;
        this.qrcodemage = qrcodemage;

    }

    public ServiceProvider(Integer sid, String spfullname, String email, String password, String address, String icnumber, String phonenumber, String service_name){
        this.sid = sid;
        this.spfullname = spfullname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.service_name = service_name;

    }

    public ServiceProvider(Integer sid, String spfullname, String email, String password, String address, String icnumber, String phonenumber, String service_name, Double average_rating){
        this.sid = sid;
        this.spfullname = spfullname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.service_name = service_name;
        this.average_rating = average_rating;

    }

    public ServiceProvider(Integer sid, String spfullname, String email, String password) {
        this.sid = sid;
        this.spfullname = spfullname;
        this.email = email;
        this.password = password;
    }

    public ServiceProvider(String spfullname, String address, String phonenumber, String service_name) {
        this.spfullname = spfullname;
        this.address = address;
        this.phonenumber = phonenumber;
        this.service_name = service_name;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSpfullname() {
        return spfullname;
    }

    public void setSpfullname(String spfullname) {
        this.spfullname = spfullname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcnumber() {
        return icnumber;
    }

    public void setIcnumber(String icnumber) {
        this.icnumber = icnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public byte[] getQrcodemg() {
        return this.qrcodemg;
    }

    public void setQrcodemg(byte[] qrcodemg) {
        this.qrcodemg = qrcodemg;
    }

    public MultipartFile getQrcodemgs() {
        return this.qrcodemgs;
    }

    public void setQrcodemgs(MultipartFile qrcodemgs) {
        this.qrcodemgs = qrcodemgs;
    }

    public String getQrcodemage() {
        return this.qrcodemage;
    }

    public void setQrcodemage(String qrcodemage) {
        this.qrcodemage = qrcodemage;
    }

    public Double getAverage_rating() {
        return this.average_rating;
    }

    public void setAverage_rating(Double average_rating) {
        this.average_rating = average_rating;
    }
}
