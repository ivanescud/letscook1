package com.simplelifestudio.letscook1.controller;
/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.dd.processbutton.FlatButton;
import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class LoginActivity extends AppCompatActivity {


    private ActionProcessButton loginBut;
    private FirebaseAuth mAuth;
    private String email;
    private String password;

    private EditText emailET;
    private EditText passwoET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBut.setProgress(20);
                activeUI(false);

                verifi();

            }
        });
    }

    private void verifi() {


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginBut.setProgress(50);
                if (emailET.getText().toString().equals("") || passwoET.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Favor ingresar los datos correctos", Toast.LENGTH_SHORT).show();
                    activeUI(true);
                } else {

                    mAuth.signInWithEmailAndPassword(emailET.getText().toString(), passwoET.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            loginBut.setProgress(100);
                            startActivity(new Intent(LoginActivity.this,resultado_listActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            loginBut.setProgress(-1);
                            Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            activeUI(true);
                            cleanET();
                            Log.d("Login", e.toString());


                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginBut.setProgress(0);
                                }
                            },1000);


                        }
                    });
                }

            }
        },1000);


    }


    private void init() {
        loginBut = findViewById(R.id.loginacLoginBut);
        emailET = findViewById(R.id.loginActEmailET);
        passwoET = findViewById(R.id.loginActPasswordET);


        loginBut.setMode(ActionProcessButton.Mode.PROGRESS);

        mAuth = FirebaseAuth.getInstance();


    }


    private void activeUI(boolean active) {
        emailET.setEnabled(active);
        passwoET.setEnabled(active);
    }

    private void cleanET() {
        emailET.setText("");
        passwoET.setText("");

    }

}