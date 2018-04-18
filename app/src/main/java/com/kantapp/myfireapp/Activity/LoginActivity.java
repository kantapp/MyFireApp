package com.kantapp.myfireapp.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.kantapp.myfireapp.R;

public class LoginActivity extends AppCompatActivity
{
    private EditText txtEmail,txtPassword;
    private Button btnLogin;
    ProgressBar loginProgressbar;
    ScrollView loginScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail=findViewById(R.id.email);
        txtPassword=findViewById(R.id.password);
        btnLogin=findViewById(R.id.email_sign_in_button);
        loginProgressbar=findViewById(R.id.login_progress);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgressbar.setVisibility(View.VISIBLE);

            }
        });
    }
}

