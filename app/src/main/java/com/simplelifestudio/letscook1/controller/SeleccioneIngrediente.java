package com.simplelifestudio.letscook1.controller;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.simplelifestudio.letscook1.R;

public class SeleccioneIngrediente extends AppCompatActivity {
    private GridView carnes;
    private GridView vegetales;
    private GridView frutas;
    private GridView granos;
    private GridView hierbaSpecies;
    private GridView lacteos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_ingredientes);
    }
}
