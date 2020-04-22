package com.simplelifestudio.letscook1.controller;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.menu.YouTubePlayerMenu;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.AdapterDireccion;
import com.simplelifestudio.letscook1.adapters.AdapterIngredientes;
import com.simplelifestudio.letscook1.model.Ingrediente;
import com.simplelifestudio.letscook1.model.Ingredientes;
import com.simplelifestudio.letscook1.model.Paso;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.Map;

public class receta_detailActivity extends AppCompatActivity {
    //bundle
    private Bundle bundle;
    //declaracion de la view recetas_detail_nestedscroll.xml
    private TextView calificacionTV;
    private TextView tituloTV;
    private TextView numeroFavoritosTV;
    private TextView numeroComentarios;
    private TextView tiempoTV;
    private TextView servidasTV;
    private TextView tipoTV;
    private TextView comentIV;
    private String videoUrl;
    //Init de la view recetas_detail.xml
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayerTracker youTubePlayerTracker;
    private YouTubePlayer youTubePlayer1;
    private ImageView backgroundIV;
    private TextView calificacion1TV;
    private ImageButton favoriteIB;
    private CardView contenedorVideo;
    //recyclerView
    private Receta receta;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Paso> paso;
    private AdapterDireccion adapterDireccion;
    private AdapterIngredientes adapterIngredientes;
    private RecyclerView direccionRV;
    private RecyclerView ingredienteRV;
    LinearLayoutManager verticalLayoutManager;
    LinearLayoutManager horizontalLayoutManager;
    //
    private int videoInicio, videoFinal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_detail);
        //PortTrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        obtenerDatos();
        youTubePlayerTracker = new YouTubePlayerTracker();
        youTubePlayerView = findViewById(R.id.recetaDetailYoutubePlayer);
        youtubePlayerListener(videoInicio, videoFinal);
        getLifecycle().addObserver(youTubePlayerView);
        cambiarActivity();
        botonFavorito();
        cambiardeActivityComentario();
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
        comentIV = findViewById(R.id.recetaDetailNumeroComentarioTV);


        //Init de la view receta_detail.xml
        backgroundIV = findViewById(R.id.recetaDetailBgIV);
        contenedorVideo = findViewById(R.id.recetaDetailContenedorVideoCV);
        calificacion1TV = findViewById(R.id.calificaion);
        favoriteIB = findViewById(R.id.recetaDetailBotonFavoritoIB);
        //recyclerView
        direccionRV = findViewById(R.id.recetaDetailDireccionRV);
        horizontalLayoutManager = new LinearLayoutManager(receta_detailActivity.this, LinearLayoutManager.HORIZONTAL,false);
        direccionRV.setLayoutManager(horizontalLayoutManager);

        ingredienteRV = findViewById(R.id.recetaDetailIngredientesRV);
        verticalLayoutManager = new LinearLayoutManager(receta_detailActivity.this,LinearLayoutManager.VERTICAL,false);
        ingredienteRV.setLayoutManager(verticalLayoutManager);

        //
        ingredientes = new ArrayList<>();
        paso = new ArrayList<>();
    }

    //Obtiene los datos y los setea
    public void obtenerDatos() {
        videoInicio = 0;
        bundle = getIntent().getExtras();
        receta = (Receta) bundle.getSerializable("receta");
         videoUrl = receta.getVideoUrl();
        calificacionTV.setText( String.valueOf(receta.getRankingRC()));
        calificacionTV.setText(String.valueOf(receta.getRankingRC()));
        numeroFavoritosTV.setText(String.valueOf(receta.getLikes().size()));
        numeroComentarios.setText(String.valueOf(receta.getComents()));
        tipoTV.setText(receta.getCategoria());

        String key1="",key2="",value1="",value2="";
      /*  for(Map.Entry<String, String> entry : receta.getIngredientes().entrySet()){
            if(key1.isEmpty()&&value1.isEmpty()){
                key1 = entry.getKey(); value1 = entry.getValue();
            }
            else{
                key2 = entry.getKey(); value2 = entry.getValue();
                ingredientes.add(new Ingrediente(R.drawable.ic_comment,value2,key1));
                key1 = "";value1 = "";key2 = "";value2 = "";
            }

        }*/
      for(Map.Entry<String,Ingrediente> entry : receta.getIngredientes().entrySet()){
          ingredientes.add(entry.getValue());
      }

        int i = 1;
        for(Map.Entry<String, String> entry : receta.getPasos().entrySet()){
            if(key1.isEmpty()&&value1.isEmpty()){
                key1 = entry.getKey(); value1 = entry.getValue();
            }
            else{
                key2 = entry.getKey(); value2 = entry.getValue();
                paso.add(new Paso(String.valueOf(i),value1,generarIconoRamdon(),R.drawable.barradebusqueda));
                key1 = "";value1 = "";key2 = "";value2 = "";
                i++;
            }

        }

        adapterIngredientes = new AdapterIngredientes(receta_detailActivity.this, ingredientes);
        adapterDireccion = new AdapterDireccion(receta_detailActivity.this, paso,videoUrl);
        direccionRV.setAdapter(adapterDireccion);
        ingredienteRV.setAdapter(adapterIngredientes);
    }

    //Crea un preview del url de un video coloca el inicio del video y el final y hara un bucle
    public void youtubePlayerListener(int videoInicio, int videoFinal) {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
            }

            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoUrl, 0);
                youTubePlayer.play();
                youTubePlayer.addListener(youTubePlayerTracker);
                backgroundIV.setVisibility(View.GONE);
                youTubePlayer1 = youTubePlayer;
                youTubePlayerView.getPlayerUiController().setFullScreenButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("videoUrl",videoUrl);
                        bundle.putSerializable("paso",paso);
                        bundle.putInt("posVideo",(int)youTubePlayerTracker.getCurrentSecond());
                        Intent intent = new Intent(getApplicationContext(),reproductor_horizontalActivity.class).putExtras(bundle);
                        startActivity(intent);
                        overridePendingTransition(R.anim.top_in, R.anim.left_out);
                    }
                });
            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                youTubePlayer.loadVideo(videoUrl, 0);
                youTubePlayer.play();
            }

            @Override
            public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {
            }
        });
    }

    //cambia de activity
    public void cambiarActivity() {
     return;
    }

    public void botonFavorito() {
        favoriteIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void cambiardeActivityComentario(){
        comentIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(receta_detailActivity.this,ComentarioActivity.class));
            }
        });

    }

    public int generarIconoRamdon(){
        return R.drawable.boton_favorito_pulsado;
       /* int inicio =0,fin=3;
        double i = Math.random()*fin-inicio+1;
        switch () {
            case 0:
                return;
            break;
            case 1:
                return;
            break;
            case 2:
                return;
            break;
            case 3:
                return;
            default:
                return ;
        }*/
    }

}


