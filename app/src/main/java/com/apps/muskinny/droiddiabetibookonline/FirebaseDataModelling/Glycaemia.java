package com.apps.muskinny.droiddiabetibookonline.FirebaseDataModelling;

import android.os.Parcel;
import android.os.Parcelable;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Marco on 26/01/18.
 */

public class Glycaemia {
    private String date_of_addition, notes_by_the_user;
    private float glycaemia;

    public Glycaemia() {
    }

    public Glycaemia(float value, String n) {
        this.glycaemia = value;
        this.notes_by_the_user = n;
        this.date_of_addition = setDate();
    }

    public Glycaemia(float value, String s, String s1) {
        this.glycaemia = value;
        this.notes_by_the_user = s;
        this.date_of_addition = s1;

    }


    private String setDate() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM, \nyyyy HH:mm");
        String date = sdf.format(new Date());
        return date;
    }

    public String getDate_of_addition() {
        return date_of_addition;
    }

    public String getNotes_by_the_user() {
        return notes_by_the_user;
    }

    public float getGlycaemia() {
        return glycaemia;
    }

}
