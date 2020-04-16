package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.Button;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.simplelifestudio.letscook1.R;

public class reproductor_horizontalActivity extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer youTubePlayer;
    YouTubePlayerTracker youTubePlayerTracker;
    Button paso1;
    Button paso2;
    Button paso3;
    Button paso4;

    String videoUrl;
    int inicioVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_horizontal);
        init();
        getLifecycle().addObserver(youTubePlayerView);
        animation();
        YouTubePlayerView();
    }

    public void init(){
        videoUrl = getIntent().getStringExtra("videoUrl");
        inicioVideo = getIntent().getIntExtra("posVideo",0);
        youTubePlayerView = findViewById(R.id.youTubePlayerViewh);
        youTubePlayerTracker = new YouTubePlayerTracker();
        Toast.makeText(this,"el video inicia en "+inicioVideo,Toast.LENGTH_SHORT).show();
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
