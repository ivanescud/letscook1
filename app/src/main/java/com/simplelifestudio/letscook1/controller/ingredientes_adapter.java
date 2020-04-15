package com.simplelifestudio.letscook1.controller;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.ingredientes;

import java.util.ArrayList;

public class ingredientes_adapter extends BaseAdapter {
ArrayList<ingredientes> arrayList = new ArrayList<>();
private Context context;
private int celda;
private  int ico;

    public ingredientes_adapter(ArrayList<ingredientes> arrayList, Context context, int celda) {
        this.arrayList = arrayList;
        this.context = context;
        this.celda = celda;
    }


    @Override
    public int getCount() {
       return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup) {
        View  view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(celda,null);

        ImageView ico = view.findViewById(R.id.celdaIngredientesIconoIV);
        TextView porcion = view.findViewById(R.id.celdaingredientePorcionTV);
        TextView producto= view.findViewById(R.id.celdaIngredienteProductoTV);

        ico.setImageURI(Uri.parse(arrayList.get(postion).getIco()));
        porcion.setText(arrayList.get(postion).getPorcion());
        producto.setText(arrayList.get(postion).getProducto());
        return view;
    }
}
