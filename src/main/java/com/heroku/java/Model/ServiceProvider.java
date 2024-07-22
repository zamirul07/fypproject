package com.heroku.java.Model;

public class ServiceProvider {

    private Integer sid;
    private String spfullname;
    private String email;
    private String password;
    private String address;
    private String icnumber;
    private String phonenumber;
    private String service_name;

public ServiceProvider() {}

    public ServiceProvider(Integer sid,  String spfullname, String email, String password, String address,  String icnumber,  String phonenumber, String service_name) {
        this.sid = sid;
        this.spfullname = spfullname;
        this.email = email;
        this.password = password;
        this.address = address;      
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.service_name = service_name;
       
    }

    public ServiceProvider(Integer sid,  String spfullname, String email, String password) {
        this.sid = sid;
        this.spfullname = spfullname;
        this.email = email;
        this.password = password;   
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
}

