package com.booking.model;

import java.util.List;

public class User {
    private String email;
    private String passWord;
    private int position_id;  //nếu là 0 => admin  1=> user  2=> guest

    public User() {
    }

    public User(String email, String passWord, int position_id) {
        this.email = email;
        this.passWord = passWord;
        this.position_id = position_id;
    }

    private String notification;

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }
}