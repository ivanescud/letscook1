package com.simplelifestudio.letscook1.controller;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.GridIngredientesAdapter;
import com.simplelifestudio.letscook1.model.Ingredientes;

import java.util.ArrayList;

public class SeleccioneIngrediente extends AppCompatActivity {
  private TabLayout tabLayout;
  private GridView gridview;
  private ArrayList<Ingredientes> ingredientesCarnes;
    private ArrayList<Ingredientes> ingredientesVegetales;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_ingredientes);
        init();
        obtenerDatos();
        tablayoutListener();
    }

    public void init(){
        tabLayout = findViewById(R.id.seleccionIngredientesTabLayout);
        gridview = findViewById(R.id.seleccion_ingredientesGridView);
        ingredientesCarnes = new ArrayList<Ingredientes>();
    }

    public void obtenerDatos(){
        ingredientesCarnes.add(new Ingredientes("Res",R.drawable.carneres));
        ingredientesCarnes.add(new Ingredientes("Pollo",R.drawable.carnepollo));
        ingredientesCarnes.add(new Ingredientes("Carnecerdo",R.drawable.carnecerdo));
        ingredientesCarnes.add(new Ingredientes("Carnevegata",R.drawable.carnevegeta));
        ingredientesCarnes.add(new Ingredientes("Res",R.drawable.carnepescado));

        ingredientesVegetales.add(new Ingredientes("Maiz",R.drawable.carnepescado));
        ingredientesVegetales.add(new Ingredientes("Maiz",R.drawable.carnepescado));
        ingredientesVegetales.add(new Ingredientes("Maiz",R.drawable.carnepescado));
        ingredientesVegetales.add(new Ingredientes("Maiz",R.drawable.carnepescado));
        ingredientesVegetales.add(new Ingredientes("Maiz",R.drawable.carnepescado));
        GridIngredientesAdapter gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesCarnes,R.layout.grid_ingredientes);
        //GridIngredientesAdapter
        gridview.setAdapter(gridIngredientesAdapter);
    }

    public void tablayoutListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              switch (tab.getPosition()){
                  case 0:
                      break;
                  case 1:
                      break;
                  case 2:
                      break;
                  case 3:
                      break;
                  case 4:
                      break;
                  case 5:
                      break;
                  default:
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
