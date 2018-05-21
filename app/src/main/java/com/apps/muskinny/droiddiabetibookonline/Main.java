package com.apps.muskinny.droiddiabetibookonline;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments.SingUp;


public class Main extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_l);
        //oadFragment(new SingUp(), SingUp.TAG);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, new SingUp());
        fragmentTransaction.commit();

    }

    /*public void loadFragment(Fragment fragment, String s)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        fragmentTransaction.addToBackStack(s);
        fragmentTransaction.commit();
    }*/

    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}
