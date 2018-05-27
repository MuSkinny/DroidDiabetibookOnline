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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.muskinny.droiddiabetibookonline.FirebaseDataModelling.User;
import com.apps.muskinny.droiddiabetibookonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUp extends Fragment implements View.OnClickListener
{
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

    public SingUp()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        registerAuth = FirebaseAuth.getInstance();
        mDbRef = FirebaseDatabase.getInstance().getReference();

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

        registerBtn =  view.findViewById(R.id.register_button);
        rEmailEdtxt =  view.findViewById(R.id.r_email);
        rPasswordEdtxt = view.findViewById(R.id.r_password);
        rNameEdtxt =  view.findViewById(R.id.r_name);
        openLogIn =  view.findViewById(R.id.passToLogIn);
        genderSpinner = view.findViewById(R.id.gender_spinner);

        spinner_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.gender_array, android.R.layout.simple_spinner_dropdown_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinner_adapter);


        registerBtn.setOnClickListener(this);
        openLogIn.setOnClickListener(this);

        return view;
    }

    public void loadFragment(Fragment fragment,String s)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        fragmentTransaction.addToBackStack(s);
        fragmentTransaction.commit();
    }

    public void SingUserUp()
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
                                createNewUser(task.getResult().getUser().getUid(), rEmail, rName);
                            }
                            else if (!task.isSuccessful())
                            {
                                showToast("Registration failed");

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
            SingUserUp();
        }
        if (v == openLogIn)
        {
            loadFragment(new LogIn(),LogIn.TAG);
        }
    }

    public void showToast(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void createNewUser(String _userid, String _email, String _name) {
        User mUser = new User(_userid, _email, _name);
        mDbRef.child(User.USERS).child(_userid).setValue(mUser);
    }


}

