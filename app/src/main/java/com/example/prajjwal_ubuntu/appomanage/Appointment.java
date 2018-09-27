package com.example.prajjwal_ubuntu.appomanage;

/**
 * Created by prajjwal-ubuntu on 26/9/18.
 */

public class Appointment {
    private String id, title, adminId, userId, date, time, description, status;

    public Appointment(){

    }

    public Appointment(String id,String title, String adminId, String userId, String date, String time, String description, String status) {
        this.id = id;
        this.title=title;
        this.adminId = adminId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }


}
