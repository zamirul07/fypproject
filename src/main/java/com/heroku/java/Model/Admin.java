package com.heroku.java.Model;

public class Admin {

    public int adminid;
    public String adminfullname;
    public String email;
    public String password;

    public Admin() {
}

public Admin(int adminid, String adminfullname, String email,String adminpassword){
    this.adminid = adminid;
    this.adminfullname = adminfullname;
    this.email = email;
    this.password = password;
}

    public int getAdminid() {
        return this.adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminfullname() {
        return this.adminfullname;
    }

    public void setAdminname(String adminfullname) {
        this.adminfullname = adminfullname;
    }

    public String getemail() {
        return this.email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return this.password;
    }

    public void setpassword(String password) {
        this.password = password;
    }


}