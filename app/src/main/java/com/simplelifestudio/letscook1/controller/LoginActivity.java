package com.simplelifestudio.letscook1.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.simplelifestudio.letscook1.R;

public class LoginActivity extends AppCompatActivity {


    private Button loginBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));finish();

            }
        });
    }



    private void init() {
        loginBut = findViewById(R.id.loginacLoginBut);
    }
}