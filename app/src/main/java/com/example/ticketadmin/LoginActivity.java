package com.example.ticketadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.example.ticketadmin.activity.Forget_Activity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    MaterialButton logInButton;
    private FirebaseAuth mAuth;
    LazyLoader lazyLoader;


    @BindView(R.id.imageView2)
    ImageView imageView;

    @BindView(R.id.welcomeTextView)
    TextView welcomeTextView;

    @BindView(R.id.continueTextView)
    TextView continueTextView;

    @BindView(R.id.userNameEditTextId)
    TextInputLayout logName;

    @BindView(R.id.passwordEditTextId)
    TextInputLayout logPassword;


    @BindView(R.id.signUpButtonId)
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lazyLoader = findViewById(R.id.lazyLoader_id);
        MaterialButton forgot_btn = findViewById(R.id.forgetPasswordButtonId);


        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);


        mAuth = FirebaseAuth.getInstance();
        logInButton = findViewById(R.id.logInButtonId);

        //Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        //startActivity(intent);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lazyLoader.addView(loader);
                userLogin();
            }
        });
        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Forget_Activity.class);
                startActivity(intent);

            }
        });


        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void regButton(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
        Pair [] pairs = new Pair[7];
        pairs[0] = new Pair<View,String>(imageView, "logo_image");
        pairs[1] = new Pair<View,String>(welcomeTextView, "logo_title");
        pairs[2] = new Pair<View,String>(continueTextView, "welcome_title");
        pairs[3] = new Pair<View,String>(logName, "userName_Etx");
        pairs[4] = new Pair<View,String>(logPassword, "password_Etx");
        pairs[5] = new Pair<View,String>(logInButton, "go_btn");
        pairs[6] = new Pair<View,String>(signUpButton, "other_btn");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
        }

        startActivity(intent);
    }

    public void regButtonClick(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }


    private void userLogin() {



        String email, password;

        email = logName.getEditText().getText().toString().trim();
        password = logPassword.getEditText().getText().toString().trim();

        if(email.isEmpty()){

            logName.setError("Enter an Email address");
            logName.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){


            logName.setError("Enter a valid Email address");
            logName.requestFocus();
            return;
        }
        if(password.isEmpty()){

            logPassword.setError("Enter an password");
            logPassword.requestFocus();
            return;
        }


        Log.d("values", email+" "+password);
        //frameLayout.setVisibility(View.VISIBLE);
        //
        lazyLoader.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                //frameLayout.setVisibility(View.GONE);
                //progressBar.setVisibility(View.GONE);
                lazyLoader.setVisibility(View.GONE);
                finish();

            } else {
                // If sign in fails, display a message to the user.
                logName.requestFocus();
                lazyLoader.setVisibility(View.GONE);
                //frameLayout.setVisibility(View.GONE);
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Email or password is not match.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
