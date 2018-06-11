package com.apps.muskinny.droiddiabetibookonline.SingUpSingInFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.muskinny.droiddiabetibookonline.FirebaseDataModelling.User;
import com.apps.muskinny.droiddiabetibookonline.R;
import com.apps.muskinny.droiddiabetibookonline.UserMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUp extends Fragment implements View.OnClickListener {
    private FirebaseAuth registerAuth;
    public static final String TAG = "REGISTER_FRAGMENT";
    private Button registerBtn;
    private EditText rEmailEdtxt, rPasswordEdtxt, rNameEdtxt;
    private View view;
    private String rName, rEmail, rPassword;
    private TextView openLogIn;
    private DatabaseReference mDbRef;
    private Spinner genderSpinner;
    private ArrayAdapter<CharSequence> spinner_adapter;

    public SingUp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerAuth = FirebaseAuth.getInstance();
        mDbRef = FirebaseDatabase.getInstance().getReference();

        if (registerAuth.getCurrentUser() != null) {
            Intent toActivityUser = new Intent(this.getActivity(), UserMain.class);
            startActivity(toActivityUser);
            showToast("Logged in");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.sing_up, container, false);

        registerBtn = view.findViewById(R.id.register_button);
        rEmailEdtxt = view.findViewById(R.id.r_email);
        rPasswordEdtxt = view.findViewById(R.id.r_password);
        rNameEdtxt = view.findViewById(R.id.r_name);
        openLogIn = view.findViewById(R.id.passToLogIn);
        genderSpinner = view.findViewById(R.id.gender_spinner);

        spinner_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.gender_array, android.R.layout.simple_spinner_dropdown_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinner_adapter);


        registerBtn.setOnClickListener(this);
        openLogIn.setOnClickListener(this);

        return view;
    }

    public void loadFragment(Fragment fragment, String s) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        //fragmentTransaction.addToBackStack(s);
        fragmentTransaction.commit();
    }

    public void SingUserUp() {
        if (checkParameters()) {
            registerAuth.createUserWithEmailAndPassword(rEmail, rPassword)
                    .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                registerAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "Email sent.");
                                                } else {
                                                    Log.d(TAG, "Failed in sending email.");

                                                }
                                            }
                                        });

                                showToast("Registration Completed!");
                                createNewUser(task.getResult().getUser().getUid(), rEmail, rName);
                            } else if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                }
                                // if user enters wrong email.
                                catch (FirebaseAuthWeakPasswordException weakPassword) {
                                    showToast("Registration failed: weak password!");
                                    // TODO: take your actions!
                                }
                                // if user enters wrong password.
                                catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                                    showToast("Registration failed: malformed email!");
                                    // TODO: Take your action
                                } catch (FirebaseAuthUserCollisionException existEmail) {
                                    showToast("Registration failed: this email already exists.\n" +
                                            "Try with another one.");

                                    // TODO: Take your action
                                } catch (Exception e) {
                                    Log.d(SingUp.TAG, "reg complete");
                                }
                            }
                        }

                    });
        }

    }

    public boolean checkParameters() {
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
    public void onClick(View v) {
        if (v == registerBtn) {
            SingUserUp();
        }
        if (v == openLogIn) {
            loadFragment(new LogIn(), LogIn.TAG);
        }
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void createNewUser(String _userid, String _email, String _name) {
        User mUser = new User(_userid, _email, _name);
        mDbRef.child(User.USERS).child(_userid).setValue(mUser);
    }


}

