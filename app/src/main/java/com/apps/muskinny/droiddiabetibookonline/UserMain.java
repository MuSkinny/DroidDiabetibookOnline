package com.apps.muskinny.droiddiabetibookonline;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.apps.muskinny.droiddiabetibookonline.UserFragments.AddDialog;
import com.apps.muskinny.droiddiabetibookonline.UserFragments.UserHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMain extends FragmentActivity implements View.OnClickListener {
    private FirebaseAuth muAuth;
    private FirebaseUser muUser;
    private FloatingActionButton openAddDialog;
    private Toolbar mToolbar;
    private ImageButton openDrawer;
    private DrawerLayout userDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        muAuth = FirebaseAuth.getInstance();
        muUser = muAuth.getCurrentUser();
        /*mToolbar = findViewById(R.id.userToolbar);
        setActionBar(mToolbar);
        mToolbar.setTitle(null);*/

        userDrawerLayout = findViewById(R.id.user_drawer);

        if (muUser == null) {
            Intent toMain = new Intent(this, Main.class);
            startActivity(toMain);
        }

        openAddDialog = findViewById(R.id.open_add_dialog);
        openDrawer = findViewById(R.id.open_drawer);

        openDrawer.setOnClickListener(this);
        openAddDialog.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
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
        if (v == openDrawer) {
            userDrawerLayout.openDrawer(Gravity.START);
        }

    }

    public void showAddDialog() {
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "ADDDIALOG");

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