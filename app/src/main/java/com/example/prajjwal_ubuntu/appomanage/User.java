package com.example.prajjwal_ubuntu.appomanage;

/**
 * Created by prajjwal-ubuntu on 24/9/18.
 */

public class User {

    private String id;
    private String name;
    private String age;
    private String mobile;
    private String email;

    public User(){

    }

    public User(String id, String name, String age, String mobile, String email) {
        this.name = name;
        this.id =id;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getId(){
        return id;
    }
}
