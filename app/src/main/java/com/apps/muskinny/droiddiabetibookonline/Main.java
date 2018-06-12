package com.apps.muskinny.droiddiabetibookonline;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments.SingUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Main extends FragmentActivity
{
    private FirebaseAuth mainAuth;
    private FirebaseUser mainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_);
        //oadFragment(new SingUp(), SingUp.TAG);

        mainAuth = FirebaseAuth.getInstance();
        mainUser = mainAuth.getCurrentUser();
        if (mainUser != null) {
            Intent toActivityUser = new Intent(this, UserMain.class);
            startActivity(toActivityUser);
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, new SingUp());
        //fragmentTransaction.add(R.id.myFrameLayout, new SingUp());
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mainAuth.getCurrentUser() != null) {
            mainAuth.signOut();
        }
        super.onDestroy();
    }
}
