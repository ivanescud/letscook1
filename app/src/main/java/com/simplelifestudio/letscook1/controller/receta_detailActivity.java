package com.simplelifestudio.letscook1.controller;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.AdapterDireccion;
import com.simplelifestudio.letscook1.adapters.AdapterIngredientes;
import com.simplelifestudio.letscook1.model.Ingrediente;
import com.simplelifestudio.letscook1.model.Paso;

import java.util.ArrayList;
import java.util.Map;

public class receta_detailActivity extends AppCompatActivity {
    //declaracion de la view recetas_detail_nestedscroll.xml
    private TextView calificacionTV;
    private TextView tituloTV;
    private TextView numeroFavoritosTV;
    private TextView numeroComentarios;
    private TextView tiempoTV;
    private TextView servidasTV;
    private TextView tipoTV;
    //Init de la view recetas_detail.xml
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayerTracker youTubePlayerTracker;
    private YouTubePlayer youTubePlayer1;
    private ImageView backgroundIV;
    private ImageButton playIB;
    private TextView calificacion1TV;
    private ImageButton favoriteIB;
    private CardView contenedorVideo;
    //recyclerView
    private AdapterDireccion adapterDireccion;
    private AdapterIngredientes adapterIngredientes;
    private RecyclerView direccionRV;
    private RecyclerView ingredienteRV;
    LinearLayoutManager verticalLayoutManager;
    LinearLayoutManager horizontalLayoutManager;
    //
    private String receta;
    private String videoUrl;
    private int videoInicio, videoFinal;
    //Firebase
    FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_detail);

        init();
        obtenerDatos();
        cambiarActivity();
        botonFavorito();
        youtubePlayerListener(videoInicio, videoFinal);
        //test direccionRV
       ArrayList<Paso> paso = new ArrayList<>();
       paso.add(new Paso("1","TEXT EXAMPLE",R.drawable.ic_comment,20));
        paso.add(new Paso("2","TEXT EXAMPLE",R.drawable.ic_favorite_border,20));
        paso.add(new Paso("3","TEXT EXAMPLE",R.drawable.ic_launcher_background,20));
        paso.add(new Paso("4","TEXT EXAMPLE",R.drawable.ic_local_bar,20));
        paso.add(new Paso("5","TEXT EXAMPLE",R.drawable.ic_import_contacts,20));
        paso.add(new Paso("6","TEXT EXAMPLE",R.drawable.ic_local_dining,20));

        //test ingredientesRV
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));
        ingredientes.add(new Ingrediente(R.drawable.ic_comment,"1/2 libra","zanahoria"));

        adapterDireccion = new AdapterDireccion(this,paso);
        adapterIngredientes = new AdapterIngredientes(this,ingredientes);
        direccionRV.setAdapter(adapterDireccion);
        ingredienteRV.setAdapter(adapterIngredientes);
    }

    public void init() {
        //Init de la view recetas_detail_nestedscroll.xml
        tituloTV = findViewById(R.id.recetaDetailTituloTV);
        numeroFavoritosTV = findViewById(R.id.recetaDetailNumeroFavoritosTV);
        calificacionTV = findViewById(R.id.recetaDetailCalificacionTV);
        numeroComentarios = findViewById(R.id.recetaDetailNumeroComentarioTV);
        tiempoTV = findViewById(R.id.recetaDetailTiempoTV);
        servidasTV = findViewById(R.id.recetaDetailServidasTV);
        tipoTV = findViewById(R.id.recetaDetailTipoTV);


        //Init de la view receta_detail.xml
        youTubePlayerTracker = new YouTubePlayerTracker();
        youTubePlayerView = findViewById(R.id.recetaDetailYoutubePlayer);
        backgroundIV = findViewById(R.id.recetaDetailBgIV);
        contenedorVideo = findViewById(R.id.recetaDetailContenedorVideoCV);
        playIB = findViewById(R.id.recetaDetailBotonPlayIB);
        calificacion1TV = findViewById(R.id.calificaion);
        favoriteIB = findViewById(R.id.recetaDetailBotonFavoritoIB);
        //recyclerView
        direccionRV = findViewById(R.id.recetaDetailDireccionRV);
        horizontalLayoutManager = new LinearLayoutManager(receta_detailActivity.this, LinearLayoutManager.HORIZONTAL,false);
        direccionRV.setLayoutManager(horizontalLayoutManager);

        ingredienteRV = findViewById(R.id.recetaDetailIngredientesRV);
        verticalLayoutManager = new LinearLayoutManager(receta_detailActivity.this,LinearLayoutManager.VERTICAL,false);
        ingredienteRV.setLayoutManager(verticalLayoutManager);
        //firebase
        db = FirebaseFirestore.getInstance();
    }

    //Obtiene los datos y los setea
    public void obtenerDatos() {
        receta = "Cereal con Leche";
        videoInicio = 0;
        videoFinal = 15;
        tituloTV.setText(receta);
        numeroComentarios.setText("200");
        db.collection("recetas").document(receta).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Map<String, Object> map = documentSnapshot.getData();
                    videoUrl = map.get("video").toString();
                    calificacionTV.setText(map.get("calificacion").toString());
                    calificacion1TV.setText(map.get("calificacion").toString());
                    numeroFavoritosTV.setText(map.get("favoritos").toString());
                    tiempoTV.setText(map.get("tiempo").toString());
                    servidasTV.setText(map.get("servida").toString());
                    tipoTV.setText(map.get("tipo").toString());
                    //crear el ciclo de reproduccion cuando ya obtenga el url
                    getLifecycle().addObserver(youTubePlayerView);
                } else {
                    Log.w("DATABASE", task.getException());
                }
            }
        });

    }

    //Crea un preview del url de un video coloca el inicio del video y el final y hara un bucle
    public void youtubePlayerListener(int videoInicio, int videoFinal) {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                if (youTubePlayerTracker.getCurrentSecond() > videoFinal) {
                    youTubePlayer.seekTo(videoInicio);
                }
            }

            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.addListener(youTubePlayerTracker);
                youTubePlayer.loadVideo(videoUrl, 0);
                backgroundIV.setVisibility(View.GONE);
                youTubePlayer.setVolume(0);
                youTubePlayer1 = youTubePlayer;
            }

        });
    }

    //cambia de activity
    public void cambiarActivity() {
        playIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(receta_detailActivity.this, reproductor_horizontalActivity.class));
                overridePendingTransition(R.anim.top_in, R.anim.left_out);
            }
        });
    }

    public void botonFavorito() {
        favoriteIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(receta_detailActivity.this, "boton presionado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

