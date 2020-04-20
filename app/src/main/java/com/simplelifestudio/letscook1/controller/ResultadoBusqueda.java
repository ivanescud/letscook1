package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.CategoriaAdapter;
import com.simplelifestudio.letscook1.model.Ingredientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultadoBusqueda extends AppCompatActivity {
private GridView gridView;
private ArrayList<String> ingredienteCarnes;
    private ArrayList<String> ingredienteVegetaleLegumbres;
    private ArrayList<String> ingredienteFrutas;
private ArrayList<Ingredientes> recetas;
private String tipo;
//fireBase
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        init();
        obtenerDatos();
    }

    public void init(){
        gridView = findViewById(R.id.categoriaGridView);
        recetas = new ArrayList<>();
        ingredienteCarnes = new ArrayList<>();

    }

    public void obtenerDatos(){
        if(tipo.equals("recetas")){
           Map<String,String>  mapingredientes = new HashMap<>();
           for(String i: ingredienteCarnes){
             mapingredientes.put("carnes",i);
           }
        }
        else{

        }
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(ResultadoBusqueda.this,recetas,R.layout.celda_categoria);
        gridView.setAdapter(categoriaAdapter);
    }
}
