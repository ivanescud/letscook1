package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.User;

public class register extends AppCompatActivity {
    EditText nombre;
    EditText apellido;
    EditText edad;
    EditText email;
    EditText contraseña;
    EditText reContraseña;
    Button registrarBt;
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
        FirebaseFirestore.getInstance();
    }

    // Añade todos los campos a la clase usuario
    public void nuevoUsuario(){
        User user = new User();
        user.setNombre(nombre.getText().toString());
        user.setApellido(apellido.getText().toString());
        user.setEdad(Integer.parseInt(edad.getText().toString()));
        user.setEmail(email.getText().toString());

        db.collection("users").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d("Database","Usuario creado en db");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    //Crea nuevo Usuario en FireBase
    public void registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),contraseña.getText().toString()).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = "548KL79KS";
                    nuevoUsuario();

                    startActivity(new Intent(register.this,HomeActivity.class));
                    finish();
                    Toast.makeText(register.this,"Usuario Creado",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(register.this,"Error:"+task.getException().getMessage().toString()+" Intente denuevo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Escuchador de Boton registrar
    public void registrar(){
        registrarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contraseña.getText().toString().equals(reContraseña.getText().toString())) {
                    if (nombre.getText().toString().isEmpty()||apellido.getText().toString().isEmpty()||
                    edad.getText().toString().isEmpty()||email.getText().toString().isEmpty()||contraseña.getText().toString().isEmpty()||
                    reContraseña.getText().toString().isEmpty()) {
                        Toast.makeText(register.this,"Debe llenar todos los parametros",Toast.LENGTH_SHORT).show();
                    } else {
                        registrarUsuario();
                    }
                }
                else{
                    Toast.makeText(register.this,"Las contraseñas no conciden",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
