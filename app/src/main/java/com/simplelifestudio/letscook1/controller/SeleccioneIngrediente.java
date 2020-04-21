package com.simplelifestudio.letscook1.controller;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.simplelifestudio.letscook1.R;

public class SeleccioneIngrediente extends AppCompatActivity {
  private TabLayout tabLayout;
  private FrameLayout gridCarnes;
    private FrameLayout gridVegetales;
    private FrameLayout gridFrutas;
    private FrameLayout gridGranos;
    private FrameLayout gridHierbas;
    private FrameLayout gridLacteos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_ingredientes);
        init();
        tablayoutListener();
    }

    public void init(){
        tabLayout = findViewById(R.id.seleccionIngredientesTabLayout);
        gridCarnes = findViewById(R.id.seleccionIngredientesCarnes);
        gridVegetales = findViewById(R.id.seleccionIngredientesVegetales);
        gridFrutas = findViewById(R.id.seleccionIngredientesFrutas);
        gridGranos = findViewById(R.id.seleccionIngredientesGranos);
        gridHierbas = findViewById(R.id.seleccionIngredientesHierbas);
        gridLacteos = findViewById(R.id.seleccionIngredientesLacteos);
    }


    public void tablayoutListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              switch (tab.getPosition()){
                  case 0:
                      gridCarnes.setVisibility(View.VISIBLE);
                      gridVegetales.setVisibility(View.GONE);
                      gridFrutas.setVisibility(View.GONE);
                      gridGranos.setVisibility(View.GONE);
                      gridHierbas.setVisibility(View.GONE);
                      gridLacteos.setVisibility(View.GONE);
                      break;
                  case 1:
                      gridCarnes.setVisibility(View.GONE);
                      gridVegetales.setVisibility(View.VISIBLE);
                      gridFrutas.setVisibility(View.GONE);
                      gridGranos.setVisibility(View.GONE);
                      gridHierbas.setVisibility(View.GONE);
                      gridLacteos.setVisibility(View.GONE);
                      break;
                  case 2:
                      gridCarnes.setVisibility(View.GONE);
                      gridVegetales.setVisibility(View.GONE);
                      gridFrutas.setVisibility(View.VISIBLE);
                      gridGranos.setVisibility(View.GONE);
                      gridHierbas.setVisibility(View.GONE);
                      gridLacteos.setVisibility(View.GONE);
                      break;
                  case 3:
                      gridCarnes.setVisibility(View.GONE);
                      gridVegetales.setVisibility(View.GONE);
                      gridFrutas.setVisibility(View.GONE);
                      gridGranos.setVisibility(View.VISIBLE);
                      gridHierbas.setVisibility(View.GONE);
                      gridLacteos.setVisibility(View.GONE);
                      break;
                  case 4:
                      gridCarnes.setVisibility(View.GONE);
                      gridVegetales.setVisibility(View.GONE);
                      gridFrutas.setVisibility(View.GONE);
                      gridGranos.setVisibility(View.GONE);
                      gridHierbas.setVisibility(View.VISIBLE);
                      gridLacteos.setVisibility(View.GONE);
                      break;
                  case 5:
                      gridCarnes.setVisibility(View.GONE);
                      gridVegetales.setVisibility(View.GONE);
                      gridFrutas.setVisibility(View.GONE);
                      gridGranos.setVisibility(View.GONE);
                      gridHierbas.setVisibility(View.GONE);
                      gridLacteos.setVisibility(View.VISIBLE);
                      break;
                  default:
                      gridCarnes.setVisibility(View.VISIBLE);
                      gridVegetales.setVisibility(View.GONE);
                      gridFrutas.setVisibility(View.GONE);
                      gridGranos.setVisibility(View.GONE);
                      gridHierbas.setVisibility(View.GONE);
                      gridLacteos.setVisibility(View.GONE);
                      break;
              }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
