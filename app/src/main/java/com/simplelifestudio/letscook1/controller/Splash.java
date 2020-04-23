package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.simplelifestudio.letscook1.R;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Splash extends AppCompatActivity {

    private MaterialProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.splashprogressbar);
        auth = FirebaseAuth.getInstance();

        progressBar.setMax(100);
        progressBar.setProgress(50);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

               FirebaseUser user = auth.getCurrentUser();

                if (user == null) {
                    progressBar.setProgress(100);
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }else {
                    progressBar.setProgress(100);
                    startActivity(new Intent(getApplicationContext(),resultado_listActivity.class));
                   finish();
                }

            }
        }, 2000);

    }
}
