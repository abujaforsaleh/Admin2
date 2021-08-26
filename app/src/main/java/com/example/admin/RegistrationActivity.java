package com.example.admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements TextWatcher {


    DataSnapshot token_snapshot, user_snapshot;
    DatabaseReference token_reference, user_reference;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    LazyLoader lazyLoader;
    private static final String TAG = "RegistrationActivity";

    @BindView(R.id.fullNameEditTextIdReg)
    TextInputLayout fullNameEditText;

    @BindView(R.id.userNameEditTextIdReg)
    TextInputLayout userNameEditText;

    @BindView(R.id.emailEditTextIdReg)
    TextInputLayout emailEditText;

    @BindView(R.id.phoneNoEditTextIdReg)
    TextInputLayout phoneNoEditText;

    @BindView(R.id.passwordEditTextIdReg)
    TextInputLayout passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        lazyLoader = findViewById(R.id.lazyLoader_id);
        lazyLoader.setVisibility(View.GONE);


        user_reference = FirebaseDatabase.getInstance().getReference("UserInfo");
        mAuth = FirebaseAuth.getInstance();

        get_UserInfo();

        /*****************************On text changed Listener*******************************/
        fullNameEditText.getEditText().addTextChangedListener(this);


    }
    private void get_UserInfo() {

        user_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_snapshot = dataSnapshot;
                Log.d("mms", "data received");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.d("mms", "data receive failed");
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    public void logInButtonClick(View view) {
        finish();
    }

    private Boolean validFullName(String val) {

        if (val.isEmpty()) {
            fullNameEditText.setError("Field can not be empty");
            return false;
        } else {
            fullNameEditText.setError(null);
            // app:errorEnabled="true" create a gap below editTextView box to show error which is helping app:counterEnabled="true"
            // For this I comment fullNameEditText.setErrorEnabled(false); to hide the gap

            // fullNameEditText.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validUserName(String val) {
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        Log.d(TAG, "validUserName: " + val.length());


        if (val.isEmpty()) {
            userNameEditText.setError("Field can not be empty");
            return false;
        } else if (val.length() < 5) {
            Log.d(TAG, "validUserName: " + val.length());
            userNameEditText.setError("User Name is too short (At least 5 character)");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            userNameEditText.setError("White Spaces are not allowed");
            return false;
        } else {
            userNameEditText.setError(null);
            // app:errorEnabled="true" create a gap below editTextView box to show error which is helping app:counterEnabled="true"
            // For this I comment fullNameEditText.setErrorEnabled(false); to hide the gap

            //userNameEditText.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validEmail(String val) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            emailEditText.setError("Field can not be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            emailEditText.setError("Invalid email address");
            return false;
        } else {
            emailEditText.setError(null);
            // app:errorEnabled="true" create a gap below editTextView box to show error which is helping app:counterEnabled="true"
            // For this I comment fullNameEditText.setErrorEnabled(false); to hide the gap

            //emailEditText.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validPhone(String val) {

        if (val.isEmpty()) {
            phoneNoEditText.setError("Field can not be empty");
            return false;
        } else {
            phoneNoEditText.setError(null);
            // app:errorEnabled="true" create a gap below editTextView box to show error which is helping app:counterEnabled="true"
            // For this I comment fullNameEditText.setErrorEnabled(false); to hide the gap

            //phoneNoEditText.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validPassword(String val) {
        String passwordPattern = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        String checkDigit = "^" + "(?=.*[0-9])" + "(?=.*[a-zA-Z])" + ".{4,}" + "$";
        String checkWhiteSpace = "^" + "(?=\\S+$)" + "(?=.*[a-zA-Z])" + ".{4,}" + "$";

        if (val.isEmpty()) {
            passwordEditText.setError("Field can not be empty");
            return false;
        } else if (val.length() < 8) {
            passwordEditText.setError("Password is too short (At least 8 character)");
            return false;
        } else if (!val.matches(checkDigit)) {
            passwordEditText.setError("Must have at least one digit");
            return false;
        } else if (!val.matches(checkWhiteSpace)) {
            passwordEditText.setError("White Spaces are not allowed");
            return false;
        } else {
            passwordEditText.setError(null);
            // app:errorEnabled="true" create a gap below editTextView box to show error which is helping app:counterEnabled="true"
            // For this I comment fullNameEditText.setErrorEnabled(false); to hide the gap

            //passwordEditText.setErrorEnabled(false);
            return true;
        }
    }

    public void regButtonClick(View view) {
        Log.d("mms", "click");

        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());
        lazyLoader.addView(loader);


        String password = passwordEditText.getEditText().getText().toString();
        String fullName = fullNameEditText.getEditText().getText().toString();
        String userName = userNameEditText.getEditText().getText().toString();
        String email = emailEditText.getEditText().getText().toString();
        String phoneNumber = phoneNoEditText.getEditText().getText().toString();

        if (!validFullName(fullName) | !validUserName(userName) | !validEmail(email) | !validPhone(phoneNumber) | !validPassword(password)) {


        }else{
            Log.d("mms", "inner click");

            register(fullName, password, userName, phoneNumber, email);
        }

        Log.d(TAG, "All Clear");

    }

    private void register(String fullName, String password, String userName, String phoneNumber, String email) {
        //checking validity of user inputs

        //checking and creating unique user profile
        if(check_name_validity(userName)){

            //progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    UserBundle userBundle = new UserBundle(fullName, userName, password, email, phoneNumber);
                    try{

                        Log.d("mms", userBundle.getEmail()+userBundle.getUserName()+userBundle.getFullName()+userBundle.getPhoneNumber()+userBundle.getPassword()+" "+Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
                        user_reference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(userBundle);
                        //user_reference.child("pulok").setValue("hi pulok");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification();
                        }
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        lazyLoader.setVisibility(View.GONE);

                    }
                    catch (Exception e){
                        lazyLoader.setVisibility(View.GONE);
                        Toast.makeText(RegistrationActivity.this, "error found", Toast.LENGTH_SHORT).show();
                    }
                    //progressBar.setVisibility(View.GONE);

                    //int history_val = token_reference.child(user_token).child("history").
                    // Sign in success, update UI with the signed-in user's information
                    //Toast.makeText(Registration.this, "Registration Sucessfull.", Toast.LENGTH_SHORT).show();

                } else {
                    //progressBar.setVisibility(View.GONE);
                    emailEditText.setError("This email is already registered");
                    emailEditText.requestFocus();
                    lazyLoader.setVisibility(View.GONE);
                    // If sign in fails, display a message to the user.
                    Toast.makeText(RegistrationActivity.this, "Register is not Sucessfull.", Toast.LENGTH_SHORT).show();
                }


            });
        }
        else{
            userNameEditText.setError("This name is already taken");
            userNameEditText.requestFocus();
            lazyLoader.setVisibility(View.GONE);
        }



    }


    boolean check_name_validity(String user_name){

        return !user_snapshot.hasChild(user_name);

    }
    /*****************************On text changed Listener Override method *******************************/
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
