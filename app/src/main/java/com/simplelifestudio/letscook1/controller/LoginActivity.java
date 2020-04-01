package com.simplelifestudio.letscook1.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.simplelifestudio.letscook1.R;

public class LoginActivity extends AppCompatActivity {


    private Button loginBut;
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



              verifi();

            }
        });
    }
    private void verifi()
    {
        if(emailET.getText().toString().equals("") || passwoET.getText().toString().equals("")){

            Toast.makeText(this,"Favor ingresar los datos correctos", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(emailET.getText().toString(),passwoET.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    Log.d("Login",e.toString());
                }
            });
        }
    }



    private void init() {
        loginBut = findViewById(R.id.loginacLoginBut);
        emailET = findViewById(R.id.loginActEmailET);
        passwoET = findViewById(R.id.loginActPasswordET);


        mAuth = FirebaseAuth.getInstance();




    }
}