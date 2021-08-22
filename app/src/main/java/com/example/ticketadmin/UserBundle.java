package com.example.ticketadmin;

public class UserBundle {

    private String fullName, userName, password, email, phoneNumber;

    public UserBundle() {

    }

    public UserBundle(String fullName, String userName, String password, String email, String phoneNumber) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
