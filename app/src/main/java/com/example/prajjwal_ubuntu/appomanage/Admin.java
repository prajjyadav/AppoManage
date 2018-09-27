package com.example.prajjwal_ubuntu.appomanage;

/**
 * Created by prajjwal-ubuntu on 24/9/18.
 */

public class Admin {

    private String id;
    private String name;
    private String email;
    private String specialization;
    private String  age;

    private String address;
    private String mobile;

    public Admin(){

    }

    public Admin(String id, String name, String email, String specialization, String age, String address, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.age = age;
        this.address = address;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }
}

