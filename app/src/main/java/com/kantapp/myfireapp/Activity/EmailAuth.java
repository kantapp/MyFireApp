package com.kantapp.myfireapp.Activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class EmailAuth extends AppCompatActivity
{

    private static final String TAG = "EmailAuth";

    private RelativeLayout mainRlayout;
    private EditText emailEdit,passwordEdit;
    private Button signButton,signoutBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();

        mainRlayout=findViewById(R.id.mainRlayout);
        emailEdit=findViewById(R.id.emailTxt);
        passwordEdit=findViewById(R.id.passwordTxt);
        signButton=findViewById(R.id.signin);
        signoutBtn=findViewById(R.id.signoutBtn);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailEdit.getText().toString().trim();
                String password=passwordEdit.getText().toString().trim();

                if(!isEmailValid(email))
                {
                    emailEdit.setError("Enter Email Id");
                }
                else if(password.isEmpty())
                {

                    passwordEdit.setError("Enter Password");
                }
                else
                {
                    emailAndPasswordSignIn(email,password);
                }
            }
        });

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                mainRlayout.setVisibility(View.VISIBLE);
                signoutBtn.setVisibility(View.GONE);
            }
        });

    }

    private void emailAndPasswordSignIn(String email,String password)
    {
        if(email.equals("") && password.equals(""))
        {
            Log.d(TAG, "emailAndPasswordSignIn: "+"Email And Password Require");
        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Log.d(TAG, "onComplete: "+"Sign in Success");
                                firebaseUser=firebaseAuth.getCurrentUser();
                                Toast.makeText(EmailAuth.this, "Welcome "+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                                mainRlayout.setVisibility(View.GONE);
                                signoutBtn.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                Toast.makeText(EmailAuth.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            mainRlayout.setVisibility(View.GONE);
            signoutBtn.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Welcome "+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            mainRlayout.setVisibility(View.VISIBLE);
            signoutBtn.setVisibility(View.GONE);
        }
    }

    public static boolean isEmailValid(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void signup(View view)
    {
        startActivity(new Intent(EmailAuth.this,EmailSignup.class));
    }
}

