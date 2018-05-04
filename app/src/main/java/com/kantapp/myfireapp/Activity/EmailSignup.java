package com.kantapp.myfireapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kantapp.myfireapp.R;

import static com.kantapp.myfireapp.Activity.EmailAuth.isEmailValid;

/**
 * Created by Kantapp Inc. on 04-05-2018.
 */
public class EmailSignup extends AppCompatActivity
{
    private static final String TAG = "EmailSignup";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_signup);

        final EditText editSignupEmail=findViewById(R.id.editSignupEmail);
        final EditText editSignupPassword=findViewById(R.id.editSignupPassword);
        Button signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmailValid(editSignupEmail.getText().toString()))
                {
                    editSignupEmail.setError("Valid Email");
                }
                else if(editSignupPassword.getText().toString().isEmpty())
                {
                    editSignupPassword.setError("Enter Password");
                }
                else
                {
                    String email=editSignupEmail.getText().toString();
                    String password=editSignupPassword.getText().toString();
                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(EmailSignup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
//                                        Toast.makeText(EmailSignup.this, "Signup Success", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(EmailSignup.this,EmailAuth.class));
                                        EmailSignup.this.finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(EmailSignup.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}
