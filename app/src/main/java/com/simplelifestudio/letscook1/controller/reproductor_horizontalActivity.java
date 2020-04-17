package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.simplelifestudio.letscook1.R;


public class reproductor_horizontalActivity extends AppCompatActivity {
    //YoutubePlayer
    private  YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    YouTubePlayerTracker youTubePlayerTracker;
    //menu
    NavigationView navigationView;
    //
    private String videoUrl;
    private int inicioVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_horizontal);
        init();
        getLifecycle().addObserver(youTubePlayerView);
        animation();
        YouTubePlayerView();
        //menu
        @SuppressLint("RestrictedApi") Menu menu = navigationView.getMenu();
        Menu submenu = menu.addSubMenu("Pasos");
        for(int i=0;i<5;i++){
           submenu.add(i,i+1,Menu.NONE,"Paso "+(i+1));
            submenu.setGroupCheckable(i,true,true);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(navigationView.getCheckedItem()!=item) {
                    navigationView.getCheckedItem().setChecked(false);
                    navigationView.setCheckedItem(item);
                }
                   Log.w("ItemC","item presionado:"+item.getItemId());
                return false;
            }
        });
    }

    public void init(){
        videoUrl = getIntent().getStringExtra("videoUrl");
        inicioVideo = getIntent().getIntExtra("posVideo",0);
        youTubePlayerView = findViewById(R.id.youTubePlayerViewh);
        youTubePlayerTracker = new YouTubePlayerTracker();
        Toast.makeText(this,"el video inicia en "+inicioVideo,Toast.LENGTH_SHORT).show();
        //menu
        navigationView = findViewById(R.id.navigation);
    }

    public void YouTubePlayerView(){
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);

            }

            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoUrl, inicioVideo);
            }
        });
    }

    public void animation(){
        Fade fade = new Fade();
        fade.setDuration(10000);
        getWindow().setEnterTransition(fade);
    }
}
