package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Ingredientes;

import java.util.ArrayList;

public class ResultadoBusqueda extends AppCompatActivity {
private GridView gridView;
private ArrayList<String> labelList;
private String label;
private String tipo;
private String categoria;
private ArrayList<Ingredientes> recetas;
private boolean recetasDisponibles;
//views
    private LinearLayout layoutPadre;
    private CardView heading;
    private TextView titleHeading;
    private FrameLayout loadingLayout;
    private FrameLayout resultadoNoEcontrado;
    private GridView gridResultado;
    private float tiempo;

//fireBase
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_categoria);
        init();
        heading.setVisibility(View.GONE);
        titleHeading.setText("Resultados de busqueda");
       loadingLayout.setVisibility(View.VISIBLE);
        obtenerDatos();
        prepararRecetas();
        noHarResultados();
        retorarHome();
    }



    public void init(){
        gridView = findViewById(R.id.categoriaGridView);
        recetas = new ArrayList<>();
        labelList = new ArrayList<>();
        label = "";
        recetasDisponibles = false;
        //view
        layoutPadre = findViewById(R.id.categoriaLayoutPadreLL);
        heading = findViewById(R.id.categoriaHeadingCV);
        titleHeading = findViewById(R.id.categoriaTitleTV);
        loadingLayout = findViewById(R.id.categoriaLoadLayoutFL);
        gridResultado = findViewById(R.id.categoriaGridView);
        resultadoNoEcontrado = findViewById(R.id.categoriaNoResultadosFL);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void obtenerDatos(){
        //prueva de tag
        tipo = "recetas";
        categoria = "breakfast";
        labelList.add("leche");
        labelList.add("cereal");
      labelList.add("cereal");
        //Generador de tag
        labelList.sort(String::compareToIgnoreCase);
        for(String i: labelList){
            if(label.isEmpty()){
                label =i;
            }
            else {
                label = label + "," + i;
            }
        }
        label = label +","+categoria;

        //consulatador hacia la nube
            db.collection(tipo).whereEqualTo("label", label).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                  if(task.isSuccessful()){
                      for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                          recetasDisponibles = true;
                      }

                  }
                  else{
                      Toast.makeText(getApplicationContext(),"Ups Algo salio mal!",Toast.LENGTH_SHORT).show();
                  }
                }
            });
        //        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(ResultadoBusqueda.this,recetas,R.layout.celda_categoria);
//        gridView.setAdapter(categoriaAdapter);
           }




    // Verificador:verifica cada dos segundos si encontro datos en firebase
     public void prepararRecetas() {
         Handler handler = new Handler();
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
              if(recetasDisponibles){
                  loadingLayout.setVisibility(View.GONE);
                  heading.setVisibility(View.VISIBLE);
                  handler.removeCallbacks(this::run);
              }
              else { prepararRecetas();}
             }
         }, 2000);

     }
//verificador final:si pasasron ocho segundos verifica una ultima vez antes de mostrar el layout de no encontrado
    public void noHarResultados() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(recetasDisponibles){loadingLayout.setVisibility(View.GONE);
                    heading.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(this::run);}
                else {
                    loadingLayout.setVisibility(View.GONE);
                    resultadoNoEcontrado.setVisibility(View.VISIBLE);
                }
            }
        }, 8000);

    }
//Retornar: retorna a la activity home
    public void retorarHome(){
        resultadoNoEcontrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }

    //Back kill: mata la aplicacion cuando presiona el de volver
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
