package com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

import com.apps.muskinny.droiddiabetibookonline.R;
import com.google.firebase.auth.FirebaseAuth;

public class SingUp extends Fragment implements View.OnClickListener
{
    private FirebaseAuth registerAuth;
    private Button registerBtn;
    private EditText rEmailEdtxt, rPasswordEdtxt, rNameEdtxt;




    public SingUp()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        registerAuth = FirebaseAuth.getInstance();
        if (registerAuth.getCurrentUser() != null)
        {
            //open UserActivity
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sing_up_fragment, container, false);
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v)
    {

    }
}
