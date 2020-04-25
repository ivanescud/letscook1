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
        ViewHolder viewHolder = new ViewHolder();

        if(view == null){
             view = layoutInflater.inflate(celda,null);
            viewHolder.NombreReceta = view.findViewById(R.id.celdaCategoriaNombreRecetaTV);
            viewHolder.mainImg = view.findViewById((R.id.recetaGridViewImgIV));
            viewHolder.numeroTop = view.findViewById(R.id.celdaGridViewTopNumberTV);
            viewHolder.idReceta="";

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.NombreReceta.setText(arrayList.get(i).getNombreRC());
        Glide.with(context).load(arrayList.get(i).getMainImgRc()).into(viewHolder.mainImg);
        viewHolder.idReceta = arrayList.get(i).getIdRC();
        viewHolder.numeroTop.setText("Top "+(i+2));
        return view;
    }

    class ViewHolder {

         ImageView mainImg;
        TextView NombreReceta;
        TextView numeroTop;
        String idReceta;

    }

}
