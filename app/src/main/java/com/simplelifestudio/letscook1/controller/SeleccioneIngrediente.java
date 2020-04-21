package com.simplelifestudio.letscook1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.GridIngredientesAdapter;
import com.simplelifestudio.letscook1.model.Ingredientes;

import java.util.ArrayList;

public class SeleccioneIngrediente extends AppCompatActivity {
  private TabLayout tabLayout;
  private GridView gridview;
  private TextView textViewlabeL;
  private   GridIngredientesAdapter gridIngredientesAdapter;
  private ArrayList<Ingredientes> ingredientesCarnes;
    private ArrayList<Ingredientes> ingredientesVegetales;
    private ArrayList<Ingredientes> ingredientesFrutas;
    private ArrayList<Ingredientes> ingredientesGranos;
    private ArrayList<Ingredientes> ingredientesHierbas;
    private ArrayList<Ingredientes> ingredientesLacteos;
    private ArrayList<Ingredientes> ingredientes;
    private ArrayList<String> label;
    private int tipoIngrediente;
    private FloatingActionButton  floatingActionButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_ingredientes);
        init();
        obtenerDatos();
        tablayoutListener();
        gridviewItemListener();
        flotingbuttomActionListener();
    }

    public void init(){
        tabLayout = findViewById(R.id.seleccionIngredientesTabLayout);
        gridview = findViewById(R.id.seleccion_ingredientesGridView);
        textViewlabeL = findViewById(R.id.seleccionIngredientesLabelTV);
        ingredientesCarnes = new ArrayList<Ingredientes>();
        ingredientesVegetales = new ArrayList<Ingredientes>();
        ingredientesFrutas = new ArrayList<>();
        ingredientesGranos = new ArrayList<>();
        ingredientesHierbas = new ArrayList<>();
        ingredientesLacteos = new ArrayList<>();
        ingredientes = new ArrayList<>();
        label = new ArrayList<>();
        floatingActionButton = findViewById(R.id.selecciomIngredientesFlotingButtom);
    }

    public void obtenerDatos(){
        ingredientesCarnes.add(new Ingredientes("Res",R.drawable.carneres));
        ingredientesCarnes.add(new Ingredientes("Pollo",R.drawable.carnepollo));
        ingredientesCarnes.add(new Ingredientes("Carne vegana",R.drawable.carnevegeta));
        ingredientesCarnes.add(new Ingredientes("Cerdo",R.drawable.carnecerdo));
        ingredientesCarnes.add(new Ingredientes("Pescado",R.drawable.carnepescado));;


        ingredientesVegetales.add(new Ingredientes("Maiz",R.drawable.carnepescado));


        ingredientesFrutas.add(new Ingredientes("Manzana",R.drawable.carnepescado));

        ingredientesFrutas.add(new Ingredientes("Manzana",R.drawable.carnepescado));

        ingredientesGranos.add(new Ingredientes("Arroz",R.drawable.carnepescado));

        ingredientesHierbas.add(new Ingredientes("Perejil",R.drawable.carnepescado));

        ingredientesLacteos.add(new Ingredientes("Leche",R.drawable.carnepescado));

        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesCarnes,R.layout.grid_ingredientes);
        gridview.setAdapter(gridIngredientesAdapter);
    }

    public void tablayoutListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tipoIngrediente = tab.getPosition();
              switch (tab.getPosition()){
                  case 0:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesCarnes,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 1:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesVegetales,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 2:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesFrutas,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 3:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesGranos,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 4:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesHierbas,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 5:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesLacteos,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  default:
                      gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesCarnes,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
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

    public void gridviewItemListener(){
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               boolean add=true;
               switch (tipoIngrediente){
                   case 0:
                       for(String j:label){
                           if(j==ingredientesCarnes.get(i).getNombreIN()){
                               label.remove(j);
                               add = false;
                           }
                       }
                       if(add) {
                           label.add(ingredientesCarnes.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;
                   case 1:
                       for(String j:label){
                           if(j==ingredientesVegetales.get(i).getNombreIN()){
                               label.remove(j);
                               add = false;
                           }
                       }
                       if(add) {
                           label.add(ingredientesVegetales.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;
                   case 2:
                       for(String j:label){
                           if(j==ingredientesFrutas.get(i).getNombreIN()){
                               label.remove(j);
                               add = false;
                           }
                       }
                       if(add) {
                           label.add(ingredientesFrutas.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;
                   case 3:
                       for(String j:label){
                           if(j==ingredientesGranos.get(i).getNombreIN()){
                               label.remove(j);
                               add = false;
                           }
                       }
                       if(add) {
                           label.add(ingredientesGranos.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;
                   case 4:for(String j:label){
                       if(j==ingredientesHierbas.get(i).getNombreIN()){
                           label.remove(j);
                           add = false;
                       }
                   }
                       if(add) {
                           label.add(ingredientesHierbas.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;
                   case 5:
                       for(String j:label){
                           if(j==ingredientesLacteos.get(i).getNombreIN()){
                               label.remove(j);
                               add = false;
                           }
                       }
                       if(add) {
                           label.add(ingredientesLacteos.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;
                   default:
                       for(String j:label){
                           if(j==ingredientesCarnes.get(i).getNombreIN()){
                               label.remove(j);
                               add = false;
                           }
                       }
                       if(add) {
                           label.add(ingredientesCarnes.get(i).getNombreIN());
                       }

                       textolabelSet();
                       break;

               }

            }
        });
    }

    public void textolabelSet(){
        textViewlabeL.setText("");
        for(String i : label){
            if(textViewlabeL.getText().toString().isEmpty()){
                textViewlabeL.setText(i);
            }
            else {
                textViewlabeL.setText(textViewlabeL.getText() + "," + i);
            }
        }
    }

    public void flotingbuttomActionListener(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(label.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"No tienes ingredientes sufucientes",Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("label", label);
                    Intent intent = new Intent(getApplicationContext(), ResultadoBusqueda.class);
                    startActivity(intent);
                }
            }
        });
    }
}
