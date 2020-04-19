package com.simplelifestudio.letscook1.controller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;


public class resultado_listActivity extends AppCompatActivity implements HomeRecetaAdapter.OnClickCell {

    private ViewPager2 mainPager;
    private HomeRecetaAdapter adapter;
    private ArrayList<Receta> recetaslist = new ArrayList<>();
    private CircleIndicator3 circleIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_list);

        mainPager = findViewById(R.id.busquedaAutoScrollPager);
        circleIndicator = findViewById(R.id.busquedaCircleIndicator);
        circleIndicator.animatePageSelected(2);
        circleIndicator.createIndicators(5,0);
        data();

        mainPager.setAdapter(adapter);

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




    private void data() {
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
        recetaslist.add(new Receta("Salmon Ahumado","Lets cook Team",url,4.5f,likes,byImg));
        recetaslist.add(new Receta("Corn Flake con leche","LetCook Team",url3,5.0f,likes,byImg));


        adapter = new HomeRecetaAdapter(recetaslist,resultado_listActivity.this,this);

    }

    @Override
    public void onClickCell(int positon) {

    }
}
