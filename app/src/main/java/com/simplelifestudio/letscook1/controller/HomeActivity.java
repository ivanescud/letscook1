package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.HomeBebidasAdapter.OnClickCell2;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.database.FireBaseData;
import com.simplelifestudio.letscook1.extra.DataHolder;
import com.simplelifestudio.letscook1.extra.SimpleDividerItemDecoration;
import com.simplelifestudio.letscook1.model.Receta;
import com.simplelifestudio.letscook1.model.User;

import java.util.ArrayList;

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
    FirebaseFirestore db;
    User curretUD;
    FireBaseData data;



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
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
        db = FirebaseFirestore.getInstance();

        recetaImgcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), SeleccioneIngrediente.class));
            }
        });

        bebidasImgcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),resultado_listActivity.class));

            }
        });







//        data.getUserData(db,getApplicationContext(),userImgCIV,userNameTV);
//
//        String userID = mAuth.getCurrentUser().getUid();
//
//
//        db.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                User currentuser =  documentSnapshot.toObject(User.class);
//
//
//            }
//        });
//
//


    }



    private  void init() {
        userImgCIV = findViewById(R.id.userImgpru);
        userNameTV = findViewById(R.id.usernamepru);
        recetaImgcell = findViewById(R.id.homeBotonRecetaBt);
         bebidasImgcell = findViewById(R.id.homeBotonbebidasIV);
          topImgcell = findViewById(R.id.homeBotontopIV);
         profileImgcell = findViewById(R.id.homeBotonperfilIV);
         recetasRV = findViewById(R.id.homerecetasRV);
         bebidasRV = findViewById(R.id.homebebidasRV);
         buscadorET = findViewById(R.id.homeBuscadorET);

        mAuth = FirebaseAuth.getInstance();
        DataHolder data = new DataHolder();
        recetas = data.getRecetas();
        bebidas = data.getBebidas();

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
