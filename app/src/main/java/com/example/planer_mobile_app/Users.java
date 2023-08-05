package com.example.planer_mobile_app;

public class Users {
    public String email;
    public String name;
    public String company;
    public String uid;

    public Users() {
    }

    public Users(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public Users(String email, String name, String uid) {
        this.email = email;
        this.name = name;
        this.uid = uid;
    }

    public Users(String email, String name, String company, String uid) {
        this.email = email;
        this.name = name;
        this.company = company;
        this.uid = uid;
    }
}
