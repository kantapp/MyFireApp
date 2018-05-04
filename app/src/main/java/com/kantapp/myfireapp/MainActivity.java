package com.kantapp.myfireapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kantapp.myfireapp.Activity.EmailAuth;
import com.kantapp.myfireapp.Activity.PhoneAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void emailAuth(View view)
    {
        go(EmailAuth.class);
    }

    public void phoneAuth(View view)
    {
        go(PhoneAuth.class);
    }

    public  void go(Class aClass)
    {
        startActivity(new Intent(getApplicationContext(),aClass));
    }
}
