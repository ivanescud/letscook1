package com.simplelifestudio.letscook1.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.simplelifestudio.letscook1.model.Ingrediente;
import com.simplelifestudio.letscook1.model.MapData;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.Map;

public class RecetaList extends AppCompatActivity {

    private RecyclerView gridlist;
    private ArrayList<Receta> recetaslist = new ArrayList<Receta>();
    private FirebaseFirestore db;
    private BusquedaRecycleAdapter adapter;
    private String tipo;
    private String style = "none";
    private Map<String, Boolean> pasDatos;
    private MapData mapData;
    int numero;



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
        style = getIntent().getStringExtra("style");

        mapData = new MapData();
        Bundle bundle = new Bundle();

        bundle = getIntent().getExtras();
        mapData = (MapData) bundle.getSerializable("datos");



        numero = getIntent().getIntExtra("numero",0);

        switch (numero){
            case 1:
                getData();
            break;
            case 2:
                getRecetasData();
            break;
            case 3:
                getRecetasDataStyle();
            break;
        }


    }




    private  void init() {

        gridlist = findViewById(R.id.recetaListGridV);


        db = FirebaseFirestore.getInstance();

        gridlist.setLayoutManager(new GridLayoutManager(RecetaList.this,2));

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

                            Receta recetas = recetaslist.get(positon);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("receta", recetas);

                            startActivity(new Intent(getApplicationContext(), RecetaList.class).putExtras(bundle));
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

    private void getRecetasDataStyle(){

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("style",style);
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



    private void getData() {
        db.collection("recetas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);


                    for(Map.Entry<String, Ingrediente> entry1 :receta.getIngredientes().entrySet()){

                        String key = entry1.getKey();


                        for(Map.Entry<String, Boolean> entry2 : mapData.getData().entrySet()){
                            String ke2 = entry2.getKey();

                            if (ke2.equals(receta.getIngredientes().get(key).getProducto())){
                                recetaslist.add(receta);
                                Log.d("Datos",""+recetaslist.size());
                            }
                        }



                    }


                }
                adapter = new BusquedaRecycleAdapter(recetaslist, getApplicationContext(), new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = recetaslist.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta", recetas);

                        startActivity(new Intent(getApplicationContext(), receta_detailActivity.class).putExtras(bundle));
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
