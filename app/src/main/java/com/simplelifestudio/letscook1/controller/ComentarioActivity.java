package com.simplelifestudio.letscook1.controller;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.ComentarioAdapter;
import com.simplelifestudio.letscook1.model.Comentarios;

import java.util.ArrayList;

public class ComentarioActivity extends AppCompatActivity {
private RecyclerView chats;
private LinearLayoutManager verticalLayoutManager;
private ArrayList<Comentarios> comentario;
    private EditText editText;
    private ImageButton sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        init();
        sendListener();
        comentario.add(new Comentarios("alex",String.valueOf(R.drawable.profileplaceholder),"Alex","hello World","322323"));
        comentario.add(new Comentarios("celia", String.valueOf(R.drawable.profileplaceholder), "Celia Jimenez", "Muy interesante tenida duda si se ponia la leche antes del cereal o el cereal antes del video MUY BUENA RECETA!", "322323"));
        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(ComentarioActivity.this,comentario);
        chats.setAdapter(comentarioAdapter);
    }

    public void init(){
        chats = findViewById(R.id.comentarioChatsRV);
        verticalLayoutManager = new LinearLayoutManager(ComentarioActivity.this,LinearLayoutManager.VERTICAL,false);
        chats.setLayoutManager(verticalLayoutManager);
        comentario = new ArrayList<>();
        editText = findViewById(R.id.comentarioEditTextET);
        sendButton = findViewById(R.id.comentarioSendIB);
    }

    public void sendListener() {

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                {
                    if(keyEvent.getAction()!=KeyEvent.ACTION_DOWN)
                    return false;

                    if (i == KeyEvent.KEYCODE_ENTER) {
                        if (!editText.getText().toString().isEmpty()) {
                            sendButton.setFocusable(true);
                            editText.setText("");
                            Toast.makeText(ComentarioActivity.this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ComentarioActivity.this, "boton presionado"+editText.getText(), Toast.LENGTH_SHORT).show();
                if (!editText.getText().toString().isEmpty()) {
                    editText.setFocusable(true);
                    editText.setText("");
                    Toast.makeText(ComentarioActivity.this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
