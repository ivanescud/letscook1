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

public class CategoriaAdapter extends BaseAdapter {
private Context context;
private ArrayList<Receta> arrayList;
private int celda;

    public CategoriaAdapter(Context context, ArrayList<Receta> arrayList, int celda) {
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
            viewHolder.idRC="";

            view.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(arrayList.get(i).getMainImgRc()).into(viewHolder.mainImgRC);
        viewHolder.mainNombreRC.setText(arrayList.get(i).getNombreRC());
        viewHolder.idRC = arrayList.get(i).getIdRC();
        return view;
    }

    class ViewHolder {
        ImageView mainImgRC;
        TextView mainNombreRC;
        String idRC;
    }
}
