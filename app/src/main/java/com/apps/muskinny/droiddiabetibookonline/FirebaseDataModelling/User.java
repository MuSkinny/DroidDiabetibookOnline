package com.apps.muskinny.droiddiabetibookonline.FirebaseDataModelling;

public class User {
    public final static String USERS = "users";

    private String email;
    private String fullname;
    //private char gender;
    private String userID;
    //private int age;

    public User() {
    }

    public User(String userID, String email, String fullname) {
        this.userID = userID;
        this.email = email;
        this.fullname = fullname;

    }

   /* public User( String email, String fullname ,String userID, char gender, int age)
    {
        this.userID = userID;
        this.email = email;
        this.fullname = fullname;
        this.age = age;
        this.gender = gender;

    }*/

    public String getEmail() {
        return email;
    }

    public String getUserID() {
        return userID;
    }

    /*public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }*/

    public String getFullname() {
        return fullname;
    }

}
