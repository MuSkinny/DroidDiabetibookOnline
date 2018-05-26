package com.apps.muskinny.droiddiabetibookonline.FirebaseDataModelling;

public class User {
    
    final static String USERS = "users";

    private String email;
    private String userID;

    public User() {
    }

    public User(String userID, String email) {
        this.userID = userID;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public String getUserID() {
        return userID;
    }

}
