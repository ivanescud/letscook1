package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Paso;

import java.util.ArrayList;


public class reproductor_horizontalActivity extends AppCompatActivity {
    //YoutubePlayer
    private  YouTubePlayerView youTubePlayerView;
    private YouTubePlayer myYouTubePlayer;
    YouTubePlayerTracker youTubePlayerTracker;
    //menu
    NavigationView navigationView;
    //Bundle get
    private String videoUrl;
    private int inicioVideo;
    private ArrayList<Paso> paso;
    //
    TextView numeroDePaso;
    TextView direccionInfo;
    ImageView iconoDireccion;
    TextView verPaso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_horizontal);
        init();
        //menu;
        menuListener();
        //card Direccion
        numeroDePaso.setText("1");
        direccionInfo.setText(paso.get(0).getIntruccion());
        iconoDireccion.setImageResource(paso.get(0).getIco());
        //youtubePlayer
        getLifecycle().addObserver(youTubePlayerView);
        YouTubePlayerView();
        //
        animation();
        verPasoEvent();

    }

    public void init(){
        //Bundle get
        Bundle bundle1 = getIntent().getExtras();
        videoUrl = bundle1.getString("videoUrl");
        inicioVideo = bundle1.getInt("posVideo",0);
        paso = (ArrayList<Paso>) bundle1.getSerializable("paso");
        //YoutubePlayer
        youTubePlayerView = findViewById(R.id.youTubePlayerViewh);
        youTubePlayerTracker = new YouTubePlayerTracker();
        Toast.makeText(this,"el video inicia en "+inicioVideo,Toast.LENGTH_SHORT).show();
        //menu
        navigationView = findViewById(R.id.navigation);
        //View
        numeroDePaso = findViewById(R.id.celdaDireccionPasoTV);
        direccionInfo = findViewById(R.id.celdaDireccionInfoTv);
        iconoDireccion = findViewById(R.id.celda_direccionIcoIV);
        verPaso = findViewById(R.id.celdaDireccionVerVideoTv);
        verPaso.setText("ver paso");
        subMenu();
    }

    //Crear sub menu pasos
    public void subMenu() {
        @SuppressLint("RestrictedApi") Menu menu = navigationView.getMenu();
        Menu submenu = menu.addSubMenu("Pasos");
        for (int i = 0; i < paso.size(); i++) {
            submenu.add(i, i + 1, Menu.NONE, "Paso " + (i + 1));
            submenu.setGroupCheckable(i, true, true);
        }
    }
    //menu listener
    public void menuListener(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(navigationView.getCheckedItem()!=item) {
                    navigationView.getCheckedItem().setChecked(false);
                    navigationView.setCheckedItem(item);
                }
                numeroDePaso.setText(paso.get(item.getItemId()-1).getNumeroPaso());
                direccionInfo.setText(paso.get(item.getItemId()-1).getIntruccion());
                Log.w("ItemC","item presionado:"+item.getItemId());
                return false;
            }
        });
    }

    //Listener ver paso
    public void verPasoEvent(){
        verPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myYouTubePlayer.seekTo(paso.get(navigationView.getCheckedItem().getItemId()-1).getPosVideo());
            }
        });
    }
    //youtubeplayer Listener
    public void YouTubePlayerView(){
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);

            }

            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoUrl, inicioVideo);
                myYouTubePlayer = youTubePlayer;
            }
        });
    }

    public void animation(){
        Fade fade = new Fade();
        fade.setDuration(10000);
        getWindow().setEnterTransition(fade);
    }
}
