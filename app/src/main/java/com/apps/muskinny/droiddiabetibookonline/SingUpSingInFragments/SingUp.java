package com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.apps.muskinny.droiddiabetibookonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUp extends Fragment implements View.OnClickListener
{
    private FirebaseAuth registerAuth;
    private Button registerBtn;
    private EditText rEmailEdtxt, rPasswordEdtxt, rNameEdtxt;
    private View view;
    private String rName, rEmail, rPassword;


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
            showToast("Logged in");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.sing_up_fragment, container, false);

        registerBtn = (Button) view.findViewById(R.id.register_button);
        rEmailEdtxt = (EditText) view.findViewById(R.id.r_email);
        rPasswordEdtxt = (EditText) view.findViewById(R.id.r_password);
        rNameEdtxt = (EditText) view.findViewById(R.id.r_name);

        registerBtn.setOnClickListener(this);

        return view;
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void SingUp()
    {
        if(checkParameters())
        {
            registerAuth.createUserWithEmailAndPassword(rEmail, rPassword)
                    .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                showToast("Registration Completed!");
                            }
                            else if (!task.isSuccessful())
                            {
                                showToast("Registration failed");
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
        }

    }

    public boolean checkParameters()
    {
       rName = rNameEdtxt.getText().toString();
       rEmail = rEmailEdtxt.getText().toString().trim();
       rPassword = rPasswordEdtxt.getText().toString().trim();

        if (TextUtils.isEmpty(rEmail)) {
            showToast("Enter email address!");

            return false;
        }

        if (TextUtils.isEmpty(rPassword)) {
            showToast("Enter password!");

            return false;
        }

        if (rPassword.length() < 8) {
            showToast("Password too short, enter minimum 8 characters!");

            return false;

        }

        if (TextUtils.isEmpty(rName)) {
            showToast("Enter your name!");

            return false;
        }

        return true;
    }


    @Override
    public void onClick(View v)
    {
        if (v == registerBtn)
        {
            SingUp();
        }
    }

    public void showToast(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}

