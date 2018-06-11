package com.apps.muskinny.droiddiabetibookonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMain extends AppCompatActivity {
    private FirebaseAuth muAuth;
    private FirebaseUser muUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_activity);

        muAuth = FirebaseAuth.getInstance();
        muUser = muAuth.getCurrentUser();
        if (muUser == null) {
            Intent toMain = new Intent(this, Main.class);
            startActivity(toMain);
        }
    }

    @Override
    public void onBackPressed() {
        if (muAuth.getCurrentUser() != null) {
            muAuth.signOut();
            Intent toMain = new Intent(this, Main.class);
            startActivity(toMain);
        }
    }
}
