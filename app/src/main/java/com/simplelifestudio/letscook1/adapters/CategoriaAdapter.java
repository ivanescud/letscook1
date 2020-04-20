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
import com.simplelifestudio.letscook1.model.Ingredientes;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

public class CategoriaAdapter extends BaseAdapter {
private Context context;
private ArrayList<Ingredientes> arrayList;
private int celda;

    public CategoriaAdapter(Context context, ArrayList<Ingredientes> arrayList, int celda) {
        this.context = context;
        this.arrayList = arrayList;
        this.celda = celda;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        if(view==null) {
            view = layoutInflater.inflate(celda,null);
            viewHolder.mainImgRC = view.findViewById(R.id.celdaCategoriaViewImgIV);
            viewHolder.mainNombreRC = view.findViewById(R.id.celdaCategoriaNombreRecetaTV);

            view.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(arrayList.get(i).getMainImg()).into(viewHolder.mainImgRC);
        viewHolder.mainNombreRC.setText(arrayList.get(i).getNombreIN());
        return view;
    }

    class ViewHolder {
        ImageView mainImgRC;
        TextView mainNombreRC;
    }
}
