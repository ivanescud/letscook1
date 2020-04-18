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
    private Bundle bundle;
    private Intent intent;
    private FirebaseFirestore db;
    private  ArrayList<Ingrediente> ingredientes = new ArrayList<>();
    private  ArrayList<Paso> paso = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_receta_detail);
        intent = new Intent(SplashRecetaDetail.this,receta_detailActivity.class);
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
        ArrayList<Paso> paso = new ArrayList<>();
        paso.add(new Paso("1", "TEXT EXAMPLE", R.drawable.ic_comment, 20));
        paso.add(new Paso("2", "TEXT EXAMPLE", R.drawable.ic_favorite_border, 10));
        paso.add(new Paso("3", "TEXT EXAMPLE", R.drawable.ic_launcher_background, 50));
        paso.add(new Paso("4", "TEXT EXAMPLE", R.drawable.ic_local_bar, 90));
        paso.add(new Paso("5", "TEXT EXAMPLE", R.drawable.ic_import_contacts, 40));
        paso.add(new Paso("6", "TEXT EXAMPLE", R.drawable.ic_local_dining, 70));

        //test ingredientesRV
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment, "1/2 libra", "zanahoria"));


    }
    public void verificarDatos(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (videoUrl != null) {
                   bundle.putString("titulo",titulo);
                    bundle.putString("calificacion",calificacion);
                    bundle.putString("calificacion",numeroFavoritos);
                    bundle.putSerializable("comentario",numeroComentario);
                    bundle.putString("tiempo",tiempo);
                    bundle.putString("servidas",servidas);
                    bundle.putString("tipo",tipo);
                    bundle.putString("videoUrl",videoUrl);
                    bundle.putSerializable("paso",paso);
                    bundle.putSerializable("ingredientes",paso);
                    startActivity(intent.putExtras(bundle));
                    finish();
                }
            }
        }, 2000);
    }
}
