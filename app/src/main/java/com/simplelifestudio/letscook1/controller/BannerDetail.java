package com.simplelifestudio.letscook1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Banner;

public class BannerDetail extends AppCompatActivity {

    private ImageView mainImgIV;
    private TextView tilteTV;
    private TextView subTitleTV;
    private TextView horaTV;
    private TextView fechaTV;
    private TextView datos;

    private Banner banner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_detail);

        init();

       Bundle b = getIntent().getExtras();
       banner = (Banner) b.getSerializable("banner");

       getData();

    }



    private  void init() {

        mainImgIV = findViewById(R.id.bannerdetailMainImgIV);
        tilteTV = findViewById(R.id.bannerdetailTitleTV);
        subTitleTV = findViewById(R.id.bannerdetailSubTitleTV);
        fechaTV = findViewById(R.id.bannerdetailfechaTV);
        horaTV = findViewById(R.id.bannerdetailhoraTV);
        datos = findViewById(R.id.bannerdetailDatosTV);



    }


    private void getData(){

        Glide.with(getApplicationContext()).load(banner.getBannerMainImg()).into(mainImgIV);
        tilteTV.setText(banner.getBannerTitle());
        subTitleTV.setText(banner.getBannersubTile());
        fechaTV.setText("Fecha:"+banner.getFecha());
        horaTV.setText("Hora: "+banner.getHora());
        datos.setText(banner.getInfo());


    }

}
