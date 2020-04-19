package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

public class Top10Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Receta> arrayList;
    private int celda;

    public Top10Adapter(Context context, ArrayList<Receta> recetas, int celda) {
        this.context = context;
        this.arrayList = recetas;
        this.celda = celda;
    }

    @Override
    public int getCount() {
        return arrayList.size() ;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(celda,null);
        ImageView mainImg;
        TextView NombreReceta;
        TextView numeroTop;
        String idReceta;


        NombreReceta = view.findViewById(R.id.celdaGridViewNombreRecetaTV);
        mainImg = view.findViewById((R.id.recetaGridViewImgIV));
        numeroTop = view.findViewById(R.id.celdaGridViewTopNumberTV);

        NombreReceta.setText(arrayList.get(i).getNombreRC());
        Glide.with(context).load(arrayList.get(i).getMainImgRc()).into(mainImg);
        idReceta = arrayList.get(i).getIdRC();
        numeroTop.setText("Top "+(i+2));
        return view;
    }
}
