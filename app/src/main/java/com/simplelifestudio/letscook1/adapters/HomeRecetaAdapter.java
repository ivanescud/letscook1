package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Ingrediente;

import java.util.ArrayList;

public class HomeRecetaAdapter  extends RecyclerView.Adapter<HomeRecetaAdapter.viewHolder> {
    ArrayList<Ingrediente> ingrediente;
    private LayoutInflater layoutInflater;
    private Context context;

    public HomeRecetaAdapter(Context context,ArrayList<Ingrediente> ingrediente) {
        this.context = context;
        this.ingrediente = ingrediente;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.celda_ingrediente,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.producto.setText(ingrediente.get(position).getProducto());
        holder.cantidad.setText(ingrediente.get(position).getCantidad());
        holder.icono.setImageResource(ingrediente.get(position).getIco());
    }

    @Override
    public int getItemCount() {
        return ingrediente.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView producto;
        TextView cantidad;
        ImageView icono;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.celdaIngredienteProductoTV);
            cantidad = itemView.findViewById(R.id.celdaingredientePorcionTV);
            icono = itemView.findViewById(R.id.celdaIngredientesIconoIV);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
