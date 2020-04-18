package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_horizontal);
        //portLand
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        init();
        //menu;
        menuListener();
        //card Direccion
        //youtubePlayer
        getLifecycle().addObserver(youTubePlayerView);
        YouTubePlayerView();
        //
        animation();

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
                dialog(item);

                Log.w("ItemC","item presionado:"+item.getItemId());
                return false;
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
    //DIALOG
    public void dialog(MenuItem item){
        TextView numeroDePaso;
        TextView direccionInfo;
        ImageView iconoDireccion;
        TextView verPaso;
        ImageView close;

        Dialog dialog = new Dialog(reproductor_horizontalActivity.this);
        dialog.setContentView(R.layout.celda_direccion_reproductor_horizontal);
        numeroDePaso = dialog.findViewById(R.id.celdaDireccionPasoTV);
        direccionInfo =  dialog.findViewById(R.id.celdaDireccionInfoTv);
        iconoDireccion =  dialog.findViewById(R.id.celda_direccionIcoIV);
        verPaso =  dialog.findViewById(R.id.celdaDireccionVerVideoTv);
        close =  dialog.findViewById(R.id.celdaDireccionCancelarIV);
        numeroDePaso.setText(paso.get(item.getItemId()-1).getNumeroPaso());
        direccionInfo.setText(paso.get(item.getItemId()-1).getIntruccion());
        iconoDireccion.setImageResource(paso.get(item.getItemId()-1).getIco());


            verPaso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myYouTubePlayer.seekTo(paso.get(navigationView.getCheckedItem().getItemId()-1).getPosVideo());
                    dialog.dismiss();
                }
            });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //Listener ver paso



    public void animation(){
        Fade fade = new Fade();
        fade.setDuration(10000);
        getWindow().setEnterTransition(fade);
    }
}
