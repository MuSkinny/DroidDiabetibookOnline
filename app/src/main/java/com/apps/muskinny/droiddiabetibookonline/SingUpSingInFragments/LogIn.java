package com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.muskinny.droiddiabetibookonline.R;

public class LogIn extends Fragment {

    static final String TAG = "LogIn Fragment";

    public LogIn() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.log_in_fragment, container, false);
    }

}