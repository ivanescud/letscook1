package com.simplelifestudio.letscook1.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.GridIngredientesAdapter;
import com.simplelifestudio.letscook1.model.Ingredientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeleccioneIngrediente{
    private Context context;
  private TabLayout tabLayout;
  private GridView gridview;
  private TextView textViewlabeL;
  private   GridIngredientesAdapter gridIngredientesAdapter;
    private FloatingActionButton  floatingActionButton;
    private ArrayList<Ingredientes> ingredientesVegetales;
    private ArrayList<Ingredientes> ingredientesCarnes;
    private ArrayList<Ingredientes> ingredientesFrutas;
    private ArrayList<Ingredientes> ingredientesGranos;
    private ArrayList<Ingredientes> ingredientesHierbas;
    private ArrayList<Ingredientes> ingredientesLacteos;
    private Map<String,Boolean> label;
    private int tipoIngrediente;
    private int datos =0;

    public SeleccioneIngrediente(Context context, TabLayout tabLayout, GridView gridview, TextView textViewlabeL, GridIngredientesAdapter gridIngredientesAdapter, FloatingActionButton floatingActionButton) {
        this.context = context;
        this.tabLayout = tabLayout;
        this.gridview = gridview;
        this.textViewlabeL = textViewlabeL;
        this.gridIngredientesAdapter = gridIngredientesAdapter;
        this.floatingActionButton = floatingActionButton;
    }


    public void init(){
        ingredientesCarnes = new ArrayList<Ingredientes>();
        ingredientesVegetales = new ArrayList<Ingredientes>();
        ingredientesFrutas = new ArrayList<>();
        ingredientesGranos = new ArrayList<>();
        ingredientesHierbas = new ArrayList<>();
        ingredientesLacteos = new ArrayList<>();
        label = new HashMap<>();
        obtenerDatos();
        tablayoutListener();
        gridviewItemListener();
        flotingbuttomActionListener();
    }

    public void obtenerDatos(){
        ingredientesCarnes.add(new Ingredientes("Res",R.drawable.carneres));
        ingredientesCarnes.add(new Ingredientes("Pollo",R.drawable.carnepollo));
        ingredientesCarnes.add(new Ingredientes("Carne vegana",R.drawable.carnevegeta));
        ingredientesCarnes.add(new Ingredientes("Cerdo",R.drawable.carnecerdo));
        ingredientesCarnes.add(new Ingredientes("Pescado",R.drawable.carnepescado));


        ingredientesVegetales.add(new Ingredientes("Apio",R.drawable.ingre_apio));
        ingredientesVegetales.add(new Ingredientes("Brocoli",R.drawable.ingre_brocoli));
        ingredientesVegetales.add(new Ingredientes("Coliflor",R.drawable.ingre_colifor));
        ingredientesVegetales.add(new Ingredientes("Zanahoria",R.drawable.ingre_zanahoria));


        ingredientesFrutas.add(new Ingredientes("Banana",R.drawable.ingre_banana));
        ingredientesFrutas.add(new Ingredientes("Uvas",R.drawable.ingre_uva));
        ingredientesFrutas.add(new Ingredientes("Cerezas",R.drawable.ingre_cerezas));
        ingredientesFrutas.add(new Ingredientes("Kiwi",R.drawable.ingre_kiwi));

        ingredientesGranos.add(new Ingredientes("Arroz",R.drawable.ingre_arroz));
        ingredientesGranos.add(new Ingredientes("Mijo",R.drawable.ingre_mijo));
        ingredientesGranos.add(new Ingredientes("Arroz integral",R.drawable.ingre_arrozintegral));
        ingredientesGranos.add(new Ingredientes("Cereal",R.drawable.ingre_cereal));

        ingredientesHierbas.add(new Ingredientes("Perejil",R.drawable.ingre_perejil));
        ingredientesHierbas.add(new Ingredientes("Romero",R.drawable.ingre_romero));
        ingredientesHierbas.add(new Ingredientes("Clavo de olor",R.drawable.ingre_clavoolor));
        ingredientesHierbas.add(new Ingredientes("Vainilla",R.drawable.ingre_vainilla));

        ingredientesLacteos.add(new Ingredientes("Leche",R.drawable.ingre_leche));
        ingredientesLacteos.add(new Ingredientes("Yogurt",R.drawable.ingre_yogurth));
        ingredientesLacteos.add(new Ingredientes("Mantequilla",R.drawable.ingre_matequilla));
        ingredientesLacteos.add(new Ingredientes("Queso",R.drawable.ingre_queso));

        gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesCarnes,R.layout.grid_ingredientes);
        gridview.setAdapter(gridIngredientesAdapter);
    }

    public void tablayoutListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tipoIngrediente = tab.getPosition();
              switch (tab.getPosition()){
                  case 0:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesCarnes,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 1:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesVegetales,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 2:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesFrutas,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 3:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesGranos,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 4:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesHierbas,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  case 5:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesLacteos,R.layout.grid_ingredientes);
                      gridview.setAdapter(gridIngredientesAdapter);
                      break;
                  default:
                      gridIngredientesAdapter = new GridIngredientesAdapter(context,ingredientesCarnes,R.layout.grid_ingredientes);
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
                         if(label.get((ingredientesCarnes.get(i).getNombreIN()))!=null){
                              label.remove(ingredientesCarnes.get(i).getNombreIN());
                          }
                          else {
                              label.put(ingredientesCarnes.get(i).getNombreIN(),true);
                          }
                       break;
                   case 1:
                       if(label.get(ingredientesVegetales.get(i).getNombreIN())!=null){
                           label.remove(ingredientesVegetales.get(i).getNombreIN());
                       }
                       else {
                           label.put(ingredientesVegetales.get(i).getNombreIN(),true);
                       }
                       break;
                   case 2:
                       if(label.get(ingredientesFrutas.get(i).getNombreIN())!=null){
                           label.remove(ingredientesFrutas.get(i).getNombreIN());
                       }
                       else {
                           label.put(ingredientesFrutas.get(i).getNombreIN(),true);
                       }
                       break;
                   case 3:
                       if(label.get(ingredientesGranos.get(i).getNombreIN())!=null){
                           label.remove(ingredientesGranos.get(i).getNombreIN());
                       }
                       else {
                           label.put(ingredientesGranos.get(i).getNombreIN(),true);
                       }
                       break;
                   case 4:
                       if(label.get(ingredientesGranos.get(i).getNombreIN())!=null){
                           label.remove(ingredientesGranos.get(i).getNombreIN());
                       }
                       else {
                           label.put(ingredientesGranos.get(i).getNombreIN(),true);
                       }
                       break;
                   case 5:
                       if(label.get(ingredientesGranos.get(i).getNombreIN())!=null){
                           label.remove(ingredientesGranos.get(i).getNombreIN());
                       }
                       else {
                           label.put(ingredientesGranos.get(i).getNombreIN(),true);
                       }
                       break;
                   default:
                       if(label.get(ingredientesCarnes.get(i).getNombreIN())!=null){
                           label.remove(ingredientesCarnes.get(i).getNombreIN());
                       }
                       else {
                           label.put(ingredientesCarnes.get(i).getNombreIN(),true);
                       }
                       break;

               }
            textolabelSet();
            }
        });
    }

    public void textolabelSet(){
        textViewlabeL.setText("");
        datos = 0;
           for(String key : label.keySet()) {
               datos++;
               if (textViewlabeL.getText().toString().isEmpty()) {
                   textViewlabeL.setText(key);
               } else {
                   textViewlabeL.setText(textViewlabeL.getText() + "," + key);
               }
           }
        }

    public void flotingbuttomActionListener(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] listalabel = new String[datos];
                int i = 0;
                if(label.isEmpty()) {
                    Toast.makeText(context,"No tienes ingredientes sufucientes",Toast.LENGTH_SHORT).show();
                }
                else{
                    for(String key : label.keySet()) {
                        listalabel[i] = key;
                        i++;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("listalabel",listalabel);
                    Intent intent = new Intent(context, ResultadoBusqueda.class).putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
    }
}
