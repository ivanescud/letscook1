package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.Top10Adapter;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

public class Top10 extends AppCompatActivity {
    ArrayList<Receta> recetas;
    ImageView top1PlaceHolder;
    TextView top1NombreReceta;
    TextView top1Ranking;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        init();
        obtenerDatos();
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        recetas.add(new Receta("IdExample","Cereal con Leche",String.valueOf(R.drawable.platillo_ejemplo)));
        Top10Adapter top10adapter = new Top10Adapter(Top10.this,recetas,R.layout.celda_viewgrid_top);
        gridView.setAdapter(top10adapter);
    }

    public void init(){
        recetas = new ArrayList<>();
        top1NombreReceta = findViewById(R.id.celdaTop1NombreRecetaTV);
        top1PlaceHolder = findViewById((R.id.celdaTop1PlaceHolderIV));
        top1Ranking = findViewById(R.id.celdaTop1RankingTV);
        gridView = findViewById(R.id.top10GridView);
    }

    public void obtenerDatos(){

    }
}
