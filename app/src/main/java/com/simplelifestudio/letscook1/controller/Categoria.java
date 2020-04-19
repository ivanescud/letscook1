package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.CategoriaAdapter;
import com.simplelifestudio.letscook1.extra.DataHolder;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

public class Categoria extends AppCompatActivity {
private GridView gridView;
private ArrayList<Receta> recetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
    }

    public void init(){
        gridView = findViewById(R.id.categoriaGridView);
        recetas = new ArrayList<>();
    }

    public void obtenerDatos(){
        DataHolder dataHolder = new DataHolder();
        recetas=dataHolder.getRecetas();
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(Categoria.this,recetas,R.layout.celda_categoria);
        gridView.setAdapter(categoriaAdapter);
    }
}
