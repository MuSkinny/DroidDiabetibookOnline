package com.apps.muskinny.droiddiabetibookonline.FirebaseDataModelling;

import java.util.ArrayList;

public class User {

    public final static String USERS = "users";
    public final static String DIABETIBOOKS = "diabetibooks";

    private String email;
    private String fullname;
    //private char gender;
    private String userID;
    private ArrayList<Glycaemia> diabetibook;
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

    public ArrayList<Glycaemia> getDiabetibook() {
        return diabetibook;
    }

    public void setDiabetibook(ArrayList<Glycaemia> diabetibook) {
        this.diabetibook = diabetibook;
    }
}
