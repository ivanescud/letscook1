package com.simplelifestudio.letscook1.controller;
/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText nombre;
    EditText apellido;
    EditText edad;
    EditText email;
    EditText contraseña;
    EditText reContraseña;
    ActionProcessButton registrarBt;
    //Fire base
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        registrar();
    }


    public void init(){
        nombre = findViewById(R.id.registroNombreTxt);
        apellido = findViewById(R.id.registroApellidoTxt);
        edad = findViewById(R.id.registroEdadTxt);
        email = findViewById(R.id.registroEmailTxt);
        contraseña = findViewById(R.id.registroContraseñaTxt);
        reContraseña = findViewById(R.id.registroRecontraseñaTxt);
        registrarBt = findViewById(R.id.loginRegisterBt);
        //FireBase
        mAuth = FirebaseAuth.getInstance();
       db = FirebaseFirestore.getInstance();

        registrarBt.setMode(ActionProcessButton.Mode.PROGRESS);
    }

    // Añade todos los campos a la clase usuario
    public void nuevoUsuario(String userId){
        User user = new User();
        user.setNombre(nombre.getText().toString());
        user.setApellido(apellido.getText().toString());
        user.setEdad(Integer.parseInt(edad.getText().toString()));
        user.setEmail(email.getText().toString());
        user.setUserID(userId);
        user.setUserImg("https://firebasestorage.googleapis.com/v0/b/letscook-1b066.appspot.com/o/MainFiles%2Fproplaceholder.jpg?alt=media&token=b264f76d-eb44-4638-9e53-aa4d4e0404e7");

        db.collection("users").document(user.getUserID()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Crea nuevo Usuario en FireBase
    public void registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),contraseña.getText().toString()).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = mAuth.getCurrentUser().getUid();
                   nuevoUsuario(userId);
                    registrarBt.setProgress(50);

                   Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           registrarBt.setProgress(100);
                           startActivity(new Intent(RegisterActivity.this,resultado_listActivity.class));
                           finish();
                           Toast.makeText(RegisterActivity.this,"Usuario Creado",Toast.LENGTH_SHORT).show();
                       }
                   },1000);


                }
                else{
                    registrarBt.setProgress(-1);
                    Toast.makeText(RegisterActivity.this,"Error:"+task.getException().getMessage().toString()+" Intente denuevo",Toast.LENGTH_SHORT).show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            registrarBt.setProgress(0);
                        }
                    },1000);

                    }
            }
        });
    }

    //Escuchador de Boton registrar
    public void registrar(){
        registrarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarBt.setProgress(20);
                if(contraseña.getText().toString().equals(reContraseña.getText().toString())) {
                    if (nombre.getText().toString().isEmpty()||apellido.getText().toString().isEmpty()||
                    edad.getText().toString().isEmpty()||email.getText().toString().isEmpty()||contraseña.getText().toString().isEmpty()||
                    reContraseña.getText().toString().isEmpty()) {
                        Toast.makeText(RegisterActivity.this,"Debe llenar todos los parametros",Toast.LENGTH_SHORT).show();
                        registrarBt.setProgress(0);
                    } else {
                        registrarUsuario();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Las contraseñas no conciden",Toast.LENGTH_SHORT).show();
                    registrarBt.setProgress(0);
                }
            }
        });

    }
}
