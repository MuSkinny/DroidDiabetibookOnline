package com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.muskinny.droiddiabetibookonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends Fragment implements View.OnClickListener
{
    private FirebaseAuth logInAuth;
    private Button LogInBtn;
    private EditText lEmailEdtxt, lPasswordEdtxt;
    private View view;
    private String lEmail, lPassword;
    public static final String TAG = "LOGIN_FRAGMENT";
    private TextView toRegister;

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

        LogInBtn =  view.findViewById(R.id.logIn_button);
        lEmailEdtxt =  view.findViewById(R.id.logIn_email);
        lPasswordEdtxt =  view.findViewById(R.id.logIn_password);
        toRegister = view.findViewById(R.id.passToRegister);

        LogInBtn.setOnClickListener(this);
        toRegister.setOnClickListener(this);

        return view;
    }

    public void showToast(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v)
    {
        if (v == LogInBtn)
        {
            logUserIn();
        }

        if (v == toRegister) {
            //control back cycle
            loadFragment(new SingUp(), SingUp.TAG);

        }
    }

    public void logUserIn()
    {
        if (checkParameters())
        {
            logInAuth.signInWithEmailAndPassword(lEmail, lPassword)
                    .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                showToast("Authentication failed!");
                            }

                            if (task.isSuccessful())
                            {
                                showToast("Authentication complete!");
                            }

                        }
                    });
        }
    }

    public boolean checkParameters()
    {
        lEmail = lEmailEdtxt.getText().toString().trim();
        lPassword = lPasswordEdtxt.getText().toString().trim();

        if (TextUtils.isEmpty(lEmail)) {
            showToast("Enter email address!");

            return false;
        }

        if (TextUtils.isEmpty(lPassword)) {
            showToast("Enter password!");

            return false;
        }
        return true;
    }

    public void loadFragment(Fragment fragment, String s) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        fragmentTransaction.addToBackStack(s);
        fragmentTransaction.commit();
    }
}
