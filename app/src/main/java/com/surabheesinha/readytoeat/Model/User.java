package com.surabheesinha.readytoeat.Model;

/**
 * Created by surabheesinha on 1/25/18.
 */

public class User {
    private String PhoneNumber;



    private String Name;
    private String Password;


    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
    }




    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getPhoneNumber() {return PhoneNumber;}
    public void setPhoneNumber(String phoneNumber) {PhoneNumber = phoneNumber;}
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
}
