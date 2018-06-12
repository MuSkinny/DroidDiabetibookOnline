package com.apps.muskinny.droiddiabetibookonline;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apps.muskinny.droiddiabetibookonline.UserFragments.AddDialog;
import com.apps.muskinny.droiddiabetibookonline.UserFragments.UserHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMain extends FragmentActivity implements View.OnClickListener {
    private FirebaseAuth muAuth;
    private FirebaseUser muUser;
    private FloatingActionButton openAddDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        muAuth = FirebaseAuth.getInstance();
        muUser = muAuth.getCurrentUser();
        if (muUser == null) {
            Intent toMain = new Intent(this, Main.class);
            startActivity(toMain);
        }

        openAddDialog = findViewById(R.id.open_add_dialog);
        openAddDialog.setOnClickListener(this);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.userLayout, new UserHome());
        //fragmentTransaction.add(R.id.myFrameLayout, new SingUp());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (muAuth.getCurrentUser() != null) {
            muAuth.signOut();
            Intent toMain = new Intent(this, Main.class);
            startActivity(toMain);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == openAddDialog) {
            showAddDialog();
        }

    }

    public void showAddDialog() {
        AddDialog addDialog = new AddDialog();
        addDialog.show(getFragmentManager(), "ADDDIALOG");

    }

}
/*
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
 */