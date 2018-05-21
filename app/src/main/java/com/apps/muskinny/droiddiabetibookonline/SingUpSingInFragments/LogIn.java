package com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.muskinny.droiddiabetibookonline.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends Fragment implements View.OnClickListener
{
    private FirebaseAuth logInAuth;
    private Button LogInBtn;
    private EditText lEmailEdtxt, lPasswordEdtxt;
    private View view;
    private String lEmail, lPassword;

    static final String TAG = "LogIn Fragment";

    public LogIn() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        logInAuth = FirebaseAuth.getInstance();
        if (logInAuth.getCurrentUser() != null)
        {
            //open UserActivity
            showToast("Logged in");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.log_in_fragment, container, false);

        LogInBtn = (Button) view.findViewById(R.id.logIn_button);
        lEmailEdtxt = (EditText) view.findViewById(R.id.logIn_email);
        lPasswordEdtxt = (EditText) view.findViewById(R.id.logIn_password);

        LogInBtn.setOnClickListener(this);

        return view;
    }

    public void showToast(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

    }
}
