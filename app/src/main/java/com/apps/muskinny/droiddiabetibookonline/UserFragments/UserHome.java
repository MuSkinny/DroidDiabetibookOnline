package com.apps.muskinny.droiddiabetibookonline.UserFragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.muskinny.droiddiabetibookonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserHome extends Fragment {

    private View v;

    public UserHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.user_home, container, false);
        return v;

    }


}
