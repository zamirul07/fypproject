package com.heroku.java.Model;

public class Services {
    
    public int service_id;
    public String service_name;
    public String service_type;
    public String service_desc;

    public Services() {
        
}

    public Services(int service_id,   String service_name, String service_type, String service_desc) {
        this.service_desc = service_desc;
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_type = service_type;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

}