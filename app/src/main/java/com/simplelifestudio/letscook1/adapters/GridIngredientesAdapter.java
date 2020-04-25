package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Ingredientes;

import java.util.ArrayList;

public class GridIngredientesAdapter extends BaseAdapter {
    Context context;
    ArrayList<Ingredientes> arrayList;
    int celda;

    public GridIngredientesAdapter(Context context, ArrayList<Ingredientes> arrayList, int celda) {
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
        if(view==null){
            view = layoutInflater.inflate(celda,null);
            viewHolder.imagenIngrediente = view.findViewById(R.id.gridIngredientesImagen);
            viewHolder.nombreIngrediente = view.findViewById(R.id.gridIngredientesNombre);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nombreIngrediente.setText(arrayList.get(i).getNombreIN());
        viewHolder.imagenIngrediente.setImageResource(arrayList.get(i).getMainImg());
        return view;
    }

    class ViewHolder {
        TextView nombreIngrediente;
        ImageView imagenIngrediente;
    }
}
