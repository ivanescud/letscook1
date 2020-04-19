package com.simplelifestudio.letscook1.controller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.BusquedaRecycleAdapter;
import com.simplelifestudio.letscook1.adapters.HomeBebidasAdapter;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.extra.DataHolder;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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



    private void init() {
        mainPager = findViewById(R.id.busquedaAutoScrollPager);
        circleIndicator = findViewById(R.id.busquedaCircleIndicator);
        recyclerView1 = findViewById(R.id.busquedaPrimeRV);
        recyclerView2 = findViewById(R.id.busquedaSegunRV);
        recyclerView3 = findViewById(R.id.busquedaTreRV);

        circleIndicator.animatePageSelected(2);

       DataHolder data = new DataHolder();
        recetaslist = data.getRecetas();
        bebidaslist = data.getBebidas();




    }




    @Override
    public void onClickCell2(int positon) {

    }

    @Override
    public void onClickCell(int positon) {

    }
}
