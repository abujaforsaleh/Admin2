package com.example.ticketadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticketadmin.LoginActivity;
import com.example.ticketadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Forget_Activity extends AppCompatActivity  {


    private Button button;
    EditText editText;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        mAuth = FirebaseAuth.getInstance();

        button=findViewById(R.id.forgetpass_button_id);
        editText=findViewById(R.id.forgetpass_email_edittext_id);
        progressBar=findViewById(R.id.progreebar_forgtetpss_id);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editText.getText().toString().trim();


                if (email.isEmpty()) {
                    editText.setError("Email is required !!");
                    editText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    editText.setError("Please Proved valid email !!");
                    editText.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                            Toast.makeText(Forget_Activity.this, "Password rest Successful cheek your email", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Forget_Activity.this, "try again ! something wrong ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}