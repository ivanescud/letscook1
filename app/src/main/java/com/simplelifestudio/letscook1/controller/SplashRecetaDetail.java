package com.simplelifestudio.letscook1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.AdapterDireccion;
import com.simplelifestudio.letscook1.model.Ingrediente;
import com.simplelifestudio.letscook1.model.Paso;

import java.util.ArrayList;
import java.util.Map;

public class SplashRecetaDetail extends AppCompatActivity {
    private String receta, titulo, numeroComentario, calificacion, numeroFavoritos, tiempo, servidas, tipo, videoUrl;
    private int videoInicio, videoFinal;
    private FirebaseFirestore db;
    private  ArrayList<Ingrediente> ingredientes = new ArrayList<>();
    private  ArrayList<Paso> paso = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_receta_detail);
        db = FirebaseFirestore.getInstance();
        obtenerDatos();
        verificarDatos();
    }


    public void obtenerDatos() {
        receta = "Cereal con Leche";
        videoInicio = 0;
        videoFinal = 15;
        db.collection("recetas").document(receta).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Map<String, Object> map = documentSnapshot.getData();
                    titulo = (map.get("titulo").toString());
                    calificacion = (map.get("calificacion").toString());
                    numeroFavoritos = (map.get("favoritos").toString());
                    tiempo = (map.get("tiempo").toString());
                    servidas = (map.get("servida").toString());
                    tipo = (map.get("tipo").toString());
                    numeroComentario = (map.get("comentario").toString());
                    videoUrl = map.get("video").toString();
                } else {
                    Log.w("DATABASE", task.getException());
                }
            }
        });
        //test direccionRV
        paso = new ArrayList<>();

        //test ingredientesRV
        ingredientes = new ArrayList<>();


    }
    public void verificarDatos(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (videoUrl != null) {
                    Log.w("SplashDebug","Titulo="+titulo);
                    Bundle bundle = new Bundle();
                    bundle.putString("titulo",titulo);
                    bundle.putString("calificacion",calificacion);
                    bundle.putString("favoritos",numeroFavoritos);
                    bundle.putSerializable("comentario",numeroComentario);
                    bundle.putString("tiempo",tiempo);
                    bundle.putString("servidas",servidas);
                    bundle.putString("tipo",tipo);
                    bundle.putString("videoUrl",videoUrl);
                    bundle.putSerializable("paso",paso);
                    bundle.putSerializable("ingredientes",ingredientes);
                    Intent intent = new Intent(getApplicationContext(),receta_detailActivity.class).putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
