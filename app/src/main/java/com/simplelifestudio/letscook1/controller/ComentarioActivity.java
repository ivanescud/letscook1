package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.ComentarioAdapter;
import com.simplelifestudio.letscook1.model.Comentarios;

import java.util.ArrayList;

public class ComentarioActivity extends AppCompatActivity {
private RecyclerView chats;
private LinearLayoutManager verticalLayoutManager;
private ArrayList<Comentarios> comentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        init();
        comentario.add(new Comentarios("alex",String.valueOf(R.drawable.profileplaceholder),"Alex","hello World","322323"));
        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(ComentarioActivity.this,comentario);
        chats.setAdapter(comentarioAdapter);
    }

    public void init(){
        chats = findViewById(R.id.comentarioChatsRV);
        verticalLayoutManager = new LinearLayoutManager(ComentarioActivity.this,LinearLayoutManager.VERTICAL,false);
        chats.setLayoutManager(verticalLayoutManager);
        comentario = new ArrayList<>();
    }
}
