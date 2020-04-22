package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.BusquedaRecycleAdapter;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

public class RecetaList extends AppCompatActivity {

    private RecyclerView gridlist;
    private ArrayList<Receta> recetaslist = new ArrayList<Receta>();
    private FirebaseFirestore db;
    private BusquedaRecycleAdapter adapter;
    private String tipo;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchonly,menu);


        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_list);

        init();


        tipo = getIntent().getStringExtra("tipo");



        gridlist.setLayoutManager(new GridLayoutManager(RecetaList.this,2));

        getRecetasData();

    }




    private  void init() {


        gridlist = findViewById(R.id.recetaListGridV);


        db = FirebaseFirestore.getInstance();
    }






        private void getRecetasData(){

            CollectionReference topF = db.collection("recetas");
            Query query = topF.whereEqualTo("type",tipo);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                        recetaslist.add(receta);
                    }

                    adapter = new BusquedaRecycleAdapter(recetaslist, getApplicationContext(), new BusquedaRecycleAdapter.OnClickCell2() {
                        @Override
                        public void onClickCell2(int positon) {

                        }
                    },1);

                    gridlist.setAdapter(adapter);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });




        }




}
