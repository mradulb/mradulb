package com.example.mradulbhargava.mobilitycaller;

/**
 * Created by MRADUL BHARGAVA on 23-12-2016.
 */
public class Contacts {
    String name;
    String contact;
    String empid;

    public Contacts(String name, String contact, String empid) {
        this.name = name;
        this.contact = contact;
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
}
