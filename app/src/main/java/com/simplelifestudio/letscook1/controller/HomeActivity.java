package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.HomeBebidasAdapter;
import com.simplelifestudio.letscook1.adapters.HomeBebidasAdapter.OnClickCell2;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.extra.DividerItemDecoration;
import com.simplelifestudio.letscook1.extra.SimpleDividerItemDecoration;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements HomeRecetaAdapter.OnClickCell, OnClickCell2 {

    private CircleImageView userImgCIV;
    private TextView userNameTV;
    private ImageView recetaImgcell;
    private ImageView bebidasImgcell;
    private ImageView topImgcell;
    private ImageView profileImgcell;
    private RecyclerView recetasRV;
    private RecyclerView bebidasRV;
    private EditText buscadorET;
    private FirebaseAuth mAuth;
    private FirebaseUser fuser;
    private HomeRecetaAdapter recetaAdapter;
    private HomeRecetaAdapter bebidasAdapter;

    ArrayList<Receta> recetas = new ArrayList<>();
    ArrayList<Receta> bebidas = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hometopbar,menu);


        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.logoutmenuBT:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            break;


        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();


        recetaImgcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),SplashRecetaDetail.class));
            }
        });

        bebidasImgcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),resultado_listActivity.class));

            }
        });

    }



    private  void init() {
        userImgCIV = findViewById(R.id.homeUserImgIV);
        userNameTV = findViewById(R.id.homeUserTileTV);
        recetaImgcell = findViewById(R.id.homeBotonRecetaBt);
         bebidasImgcell = findViewById(R.id.homeBotonbebidasIV);
          topImgcell = findViewById(R.id.homeBotontopIV);
         profileImgcell = findViewById(R.id.homeBotonperfilIV);
         recetasRV = findViewById(R.id.homerecetasRV);
         bebidasRV = findViewById(R.id.homebebidasRV);
         buscadorET = findViewById(R.id.homeBuscadorET);

        mAuth = FirebaseAuth.getInstance();
        Map<String, Boolean> likes =  new HashMap<>();
        likes.put("as5e4c5ed",true);
        likes.put("54fhjus",true);
        likes.put("as4ve",true);

        String byImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRcCL-1WAQCU623E3mfzm86LmhYcPrlW5A2wbE7K9yURaWW_GxV&usqp=CAU";
        String byImg2 = "https://cdn-theforkmanager.external.thefork.tech/static/styles/blog_article_header_image/public/wp-blog/3-el-tenedor-atraer-clientes-bartender-restaurante.jpg?itok=RVUU9aiQ";
        Map<String, String> ingrediete  = new HashMap<>();




        String url = "https://s1.eestatic.com/2019/09/12/cocinillas/recetas/pescado-y-marisco/Salmon-Pescado-Pescado_y_marisco_428717504_134377937_1024x576.jpg";
        String url2 = "https://okdiario.com/img/2018/07/02/receta-de-cocktail-de-martini-con-limon-1-655x368.jpg";
        String url3 = "https://images.freeimages.com/images/premium/previews/1716/17166547-cornflakes-with-pouring-milk.jpg";
        String url4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTBQ0FnVuAGcbUAxpmdB4Q4grLaKDoaHtvEfkE5PQn1hLcxoxhJ&usqp=CAU";
        recetas.add(new Receta("Salmon Ahumado","Lets cook Team",url,4.5f,likes,byImg));
        recetas.add(new Receta("Corn Flake con leche","LetCook Team",url3,5.0f,likes,byImg));

        bebidas.add(new Receta("Martiny Frances","LetsCook Team",url2,3.7f,likes,byImg2));
        bebidas.add(new Receta("Blue Sky","Jorge Lopez",url4,4.0f,likes,byImg2));

        recetaAdapter = new HomeRecetaAdapter(recetas,HomeActivity.this,this);
        bebidasAdapter = new HomeRecetaAdapter(bebidas,HomeActivity.this,this);


       LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recetasRV.setLayoutManager(horizontalLayoutManager);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL,false);
        bebidasRV.setLayoutManager(horizontalLayoutManager2);

        //recetasRV.addItemDecoration(new DividerItemDecoration(HomeActivity.this,null));
        recetasRV.addItemDecoration(new SimpleDividerItemDecoration(HomeActivity.this));

        recetasRV.setAdapter(recetaAdapter);
        bebidasRV.setAdapter(bebidasAdapter);







    }

    @Override
    public void onClickCell(int positon) {
        Bundle data = new Bundle();
        Receta recetapass = recetas.get(positon);
        data.putSerializable("data",recetapass);
        Intent intent = new Intent (getApplicationContext(),receta_detailActivity.class).putExtra("data",data);
        startActivity(intent);
    }


    @Override
    public void onClickCell2(int positon) {
        Bundle data = new Bundle();
        Receta recetapass = bebidas.get(positon);
        data.putSerializable("data",recetapass);
        Intent intent = new Intent (getApplicationContext(),receta_detailActivity.class).putExtra("data",data);
        startActivity(intent);
    }
}
