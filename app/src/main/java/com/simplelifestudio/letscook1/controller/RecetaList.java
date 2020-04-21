package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

public class RecetaList extends AppCompatActivity {

    private GridView gridlist;
    private ArrayList<Receta> recetas = new ArrayList<Receta>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_list);
    }




    private  void init() {

       // gridlist = findViewById(R.id.recetaListGridV);



    }
}
