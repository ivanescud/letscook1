package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.simplelifestudio.letscook1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private CircleImageView userImgCIV;
    private TextView userNameTV;
    private ImageView recetaImgcell;
    private ImageView tragosImgcell;
    private ImageView topImgcell;
    private ImageView profileImgcell;
    private RecyclerView recetasRV;
    private RecyclerView bebidasRV;
    private EditText buscadorET;
    private FirebaseAuth mAuth;
    private FirebaseUser fuser;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hometopbar,menu);


        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.logoutmenuBT:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            break;


        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();


        recetaImgcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),receta_detailActivity.class));
            }
        });

    }



    private  void init() {
        userImgCIV = findViewById(R.id.homeUserImgIV);
        userNameTV = findViewById(R.id.homeUserTileTV);
        recetaImgcell = findViewById(R.id.homeBotonRecetaBt);
         tragosImgcell = findViewById(R.id.homeBotonbebidasIV);
          topImgcell = findViewById(R.id.homeBotontopIV);
         profileImgcell = findViewById(R.id.homeBotonperfilIV);
         recetasRV = findViewById(R.id.homerecetasRV);
         bebidasRV = findViewById(R.id.homebebidasRV);
         buscadorET = findViewById(R.id.homeBuscadorET);

        mAuth = FirebaseAuth.getInstance();

    }

}
