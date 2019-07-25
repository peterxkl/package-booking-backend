package com.oocl.packagebooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Package {
    @Id
    private String id;

    private String name;

    private String phone;

    private int status;

    private String time;

    public Package() {
    }

    public Package(String id) {
        this.id = id;
    }

    public Package(String id, String name, String phone, int status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    public Package(String id, String name, String phone, int status, String time) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.time = time;
    }

    public Package(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Package(String id, String time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
