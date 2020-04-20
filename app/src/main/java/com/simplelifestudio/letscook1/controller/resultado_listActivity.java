package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.BusquedaRecycleAdapter;
import com.simplelifestudio.letscook1.adapters.CategoriaAdapter;
import com.simplelifestudio.letscook1.adapters.HomeBebidasAdapter;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.extra.DataHolder;
import com.simplelifestudio.letscook1.model.Ingredientes;
import com.simplelifestudio.letscook1.model.Receta;
import com.simplelifestudio.letscook1.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;


public class resultado_listActivity extends AppCompatActivity implements BusquedaRecycleAdapter.OnClickCell2 , HomeRecetaAdapter.OnClickCell {

    private ViewPager2 mainPager;
    private HomeRecetaAdapter adapter;
    private ArrayList<Receta> recetaslist = new ArrayList<>();
    private ArrayList<Receta> bebidaslist = new ArrayList<Receta>();
    private CircleIndicator3 circleIndicator;
    BusquedaRecycleAdapter recycleAdapter;
    BusquedaRecycleAdapter recycleAdapter2;
    BusquedaRecycleAdapter recycleAdapter3;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private FirebaseAuth mAuth;
    private FloatingActionButton floatbut;
    private AlertDialog alertDialog;
    private FirebaseFirestore db;
    private CircleImageView useImgCIV;
    private TextView userNameTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_list);

        init();




        adapter = new HomeRecetaAdapter(recetaslist,resultado_listActivity.this,this);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(horizontalLayoutManager);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);

        LinearLayoutManager horizontalLayoutManager3 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(horizontalLayoutManager3);



        recycleAdapter = new BusquedaRecycleAdapter(recetaslist,resultado_listActivity.this,this,1);
        recycleAdapter2 = new BusquedaRecycleAdapter(bebidaslist,resultado_listActivity.this,this,2);
        recycleAdapter3 = new BusquedaRecycleAdapter(recetaslist,resultado_listActivity.this,this,3);

        mainPager.setAdapter(adapter);
        recyclerView1.setAdapter(recycleAdapter);
        recyclerView2.setAdapter(recycleAdapter2);
        recyclerView3.setAdapter(recycleAdapter3);
        circleIndicator.setViewPager(mainPager);
       // circleIndicator.setViewPager(mainPager);

        getUserDataFirebase();


        floatbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu,menu);

        MenuItem searchItem = menu.findItem(R.id.searchbar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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

    private void init() {
        mainPager = findViewById(R.id.busquedaAutoScrollPager);
        circleIndicator = findViewById(R.id.busquedaCircleIndicator);
        recyclerView1 = findViewById(R.id.busquedaPrimeRV);
        recyclerView2 = findViewById(R.id.busquedaSegunRV);
        recyclerView3 = findViewById(R.id.busquedaTreRV);
        floatbut = findViewById(R.id.busquedafloatinBut);
        userNameTV = findViewById(R.id.homeUserTileTV);
        useImgCIV = findViewById(R.id.homeUserImgIV);

        circleIndicator.animatePageSelected(2);

       DataHolder data = new DataHolder();
        recetaslist = data.getRecetas();
        bebidaslist = data.getBebidas();


        mAuth = FirebaseAuth.getInstance();

    }




    @Override
    public void onClickCell2(int positon) {

    }

    @Override
    public void onClickCell(int positon) {

    }


    private void  dialog () {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.seleccion, null, false);
        builder.setView(dialogView);
        ImageButton recetaIB = dialogView.findViewById(R.id.homedialogRcBT);
        ImageButton bebidasIB = dialogView.findViewById(R.id.homedialogBDBT);
        Button salirBT = dialogView.findViewById(R.id.homedialogsalirBT);

        recetaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog2();

            }
        });

        bebidasIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogbebidas();

            }
        });



        salirBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }




    void dialog2(){

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_categoria, null, false);
        builder.setView(dialogView);
        GridView gridView = dialogView.findViewById(R.id.categoriaGridView);

        DataHolder dataHolder = new DataHolder();
        ArrayList<Ingredientes> categoria = new ArrayList<>();
        categoria = dataHolder.getCategoria();
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(resultado_listActivity.this,categoria,R.layout.celda_categoria);
        gridView.setAdapter(categoriaAdapter);


        alertDialog = builder.create();
        alertDialog.show();
    }



    private void  dialogbebidas () {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.seleccion, null, false);
        builder.setView(dialogView);
        ImageButton bebidasIB = dialogView.findViewById(R.id.homedialogRcBT);
        ImageButton recetaIB = dialogView.findViewById(R.id.homedialogBDBT);
        TextView ftitleTV = dialogView.findViewById(R.id.homedialFTV);
        TextView secondTV = dialogView.findViewById(R.id.homedialSTV);
        Button salirBT = dialogView.findViewById(R.id.homedialogsalirBT);
        salirBT.setVisibility(View.GONE);

        recetaIB.setImageResource(R.drawable.bebidasalc);
        secondTV.setText("Con Alchol");
        ftitleTV.setText("Sin Alchol");
        bebidasIB.setImageResource(R.drawable.bebidasnoalc);


        recetaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        bebidasIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(getApplicationContext(),Categoria.class).putExtra("Dato","bebidas"));

            }
        });



        salirBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }




    private void getUserDataFirebase() {

        String userID = mAuth.getCurrentUser().getProviderId().toString();
        Log.d("Firebase",userID);

        db = FirebaseFirestore.getInstance();

        db.collection("users").document(userID)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                //Log.d("Firebase", documentSnapshot.getData().get("nombre").toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Firebase",e.getMessage());
            }
        });
//       db.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                Map<String,Object> map = task.getResult().getData();
//
//
//
//
//
//               Log.d("Firebase", map.get("nombre").toString());
//
//
////
////                usuario.setNombre(map.get("nombre").toString());
////                usuario.setApellido(map.get("apellido").toString());
////                usuario.setUserImg(map.get("userImg").toString());
////                usuario.setEdad(Integer.parseInt(map.get("edad").toString()));
////                usuario.setUserID(map.get("userID").toString());
//
//
////                Glide.with(getApplicationContext()).load(usuario.getUserImg()).into(useImgCIV);
////                userNameTV.setText(usuario.getNombre()+" "+usuario.getApellido());
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });


    }

}
