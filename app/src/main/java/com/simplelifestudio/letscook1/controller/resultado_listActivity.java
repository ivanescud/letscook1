package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.BusquedaRecycleAdapter;
import com.simplelifestudio.letscook1.adapters.CategoriaAdapter;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.database.FireBaseData;
import com.simplelifestudio.letscook1.extra.DataHolder;
import com.simplelifestudio.letscook1.model.Banner;
import com.simplelifestudio.letscook1.model.Ingredientes;
import com.simplelifestudio.letscook1.model.Receta;
import com.simplelifestudio.letscook1.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;


public class resultado_listActivity extends AppCompatActivity implements BusquedaRecycleAdapter.OnClickCell2, HomeRecetaAdapter.OnClickCell {

    private final static String TAGU = "FIREBASEUSER";


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
    private User currendUser;
    private FrameLayout loadingLayout;
    private boolean userReady = false;
    private ArrayList<Banner> nannerList = new ArrayList<>();
    private ArrayList<Receta> recetasList = new ArrayList<>();
    private ArrayList<Receta> bebidasList = new ArrayList<>();
    private ArrayList<Receta>toplist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_list);

        init();

        adapter = new HomeRecetaAdapter(recetaslist, resultado_listActivity.this, this);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(horizontalLayoutManager);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);

        LinearLayoutManager horizontalLayoutManager3 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(horizontalLayoutManager3);





        mainPager.setAdapter(adapter);
        getRecetasData();
        getBebidasData();
        getTopData();

        circleIndicator.setViewPager(mainPager);
        // circleIndicator.setViewPager(mainPager);

        floatbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        prepararUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
        userNameTV = findViewById(R.id.busquedaUserNameTV);
        useImgCIV = findViewById(R.id.busquedaUserImgCIV);
        //loadingLayout = findViewById(R.id.busquedaMainLoadLayoutFL);
        circleIndicator.animatePageSelected(2);




        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

       // loadingLayout.setVisibility(View.VISIBLE);






    }


    @Override
    public void onClickCell2(int positon) {

    }

    @Override
    public void onClickCell(int positon) {

    }


    private void dialog() {

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


    void dialog2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_categoria, null, false);
        builder.setView(dialogView);
        GridView gridView = dialogView.findViewById(R.id.categoriaGridView);

        DataHolder dataHolder = new DataHolder();
        ArrayList<Ingredientes> categoria = new ArrayList<>();
        categoria = dataHolder.getCategoria();
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(resultado_listActivity.this, categoria, R.layout.celda_categoria);
        gridView.setAdapter(categoriaAdapter);


        alertDialog = builder.create();
        alertDialog.show();
    }


    private void dialogbebidas() {

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

        String userID = mAuth.getCurrentUser().getUid();

        Log.d(TAGU,userID);

        db.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot != null){
                    currendUser =  documentSnapshot.toObject(User.class);
                    userReady = true;
                    Glide.with(getApplicationContext()).load(currendUser.getUserImg()).placeholder(R.drawable.proplaceholder).into(useImgCIV);
                    String username = currendUser.getNombre() + " " + currendUser.getApellido();
                    userNameTV.setText(username);
                }



            }
        });



    }



    public void prepararUser() {
        getUserDataFirebase();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!userReady){


                    AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

                    View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lodingsplash, null, false);
                    builder.setView(dialogView);


                    alertDialog = builder.create();
                    alertDialog.show();
                    handler.removeCallbacks(this::run);

                    Integer imgTag = (Integer) useImgCIV.getTag();
                    alertDialog.dismiss();


                }
                else { prepararUser();}
            }
        }, 100);

    }

    private void getBannerData(){

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


    private void getRecetasData(){

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("type","recetas");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    recetaslist.add(receta);
                }

                recycleAdapter = new BusquedaRecycleAdapter(recetaslist, resultado_listActivity.this, resultado_listActivity.this::onClickCell2, 1);
                recyclerView1.setAdapter(recycleAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });




    }


    private void getBebidasData(){

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("type","bebidas");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    bebidasList.add(receta);
                }

                recycleAdapter2 = new BusquedaRecycleAdapter(bebidasList, resultado_listActivity.this, resultado_listActivity.this::onClickCell2, 1);
                recyclerView2.setAdapter(recycleAdapter2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }



    private void getTopData(){
        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereGreaterThanOrEqualTo("rankingRC",4);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    toplist.add(receta);
                }

                recycleAdapter3 = new BusquedaRecycleAdapter(toplist, resultado_listActivity.this, resultado_listActivity.this::onClickCell2, 3);
                recyclerView3.setAdapter(recycleAdapter3);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


}
