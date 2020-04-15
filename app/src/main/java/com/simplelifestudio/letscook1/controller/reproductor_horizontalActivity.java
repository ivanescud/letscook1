package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Fade;

import com.simplelifestudio.letscook1.R;

public class reproductor_horizontalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_horizontal);
        animation();
    }

    public void animation(){
        Fade fade = new Fade();
        fade.setDuration(10000);
        getWindow().setEnterTransition(fade);
    }
}
