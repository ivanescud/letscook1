package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.simplelifestudio.letscook1.R;

public class HomeActivity extends AppCompatActivity {

    ImageView but;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        but = findViewById(R.id.homeBotonRecetaBt);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),receta_detailActivity.class));
            }
        });

    }

}
