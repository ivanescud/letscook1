package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simplelifestudio.letscook1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerBut;
    private Button loginBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            init();
        registerBut.setOnClickListener(this);
        loginBut.setOnClickListener(this);

    }




    private void init() {
        registerBut = findViewById(R.id.loginRegisterBt);
        loginBut = findViewById(R.id.loginbut);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.loginRegisterBt:

                startActivity(new Intent(MainActivity.this,register.class));

                break;

            case R.id.loginbut:

                 startActivity(new Intent(MainActivity.this,LoginActivity.class));

                break;

            default:
                break;

        }


    }
}
