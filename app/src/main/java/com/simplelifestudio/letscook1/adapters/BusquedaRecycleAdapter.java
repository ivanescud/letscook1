package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;



public class BusquedaRecycleAdapter extends RecyclerView.Adapter<BusquedaRecycleAdapter.ViewHolder> {

    ArrayList<Receta> recetas;
    private Context context;
    LayoutInflater layoutInflater;
    OnClickCell2 onClickCell;
    private int tipo;


    public BusquedaRecycleAdapter(ArrayList<Receta> recetas, Context context, OnClickCell2 onClickCell, int tipo) {
        this.recetas = recetas;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.onClickCell = onClickCell;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public BusquedaRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cellbusqueda, parent, false);
        return new ViewHolder(view, onClickCell);
    }

    @Override
    public void onBindViewHolder(@NonNull BusquedaRecycleAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(recetas.get(position).getMainImgRc()).into(holder.mainImgIV);
        holder.titleTV.setText(recetas.get(position).getNombreRC());
        holder.timeTV.setText(recetas.get(position).getTiempo());
        holder.likesTV.setText(String.valueOf(recetas.get(position).getLikes().size()));
        holder.rankTV.setText(String.valueOf(recetas.get(position).getRankingRC()));


    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mainImgIV;
        TextView likesTV;
        ImageView iconoIV;
        TextView titleTV;
        TextView rankTV;
        TextView timeTV;

        OnClickCell2 onClickCell;

        public ViewHolder(@NonNull View itemView, OnClickCell2 onClickCell) {
            super(itemView);
            mainImgIV = itemView.findViewById(R.id.cellbusquedaMainImgIV);
            likesTV = itemView.findViewById(R.id.cellbusquedalikeTv);
            titleTV = itemView.findViewById(R.id.cellbusquedaTitleTV);
            iconoIV = itemView.findViewById(R.id.cellbusquedalitImgIV);
            rankTV = itemView.findViewById(R.id.cellbusquedaRankTV);
            timeTV = itemView.findViewById(R.id.cellbusquedaTiempoTV);

            this.onClickCell = onClickCell;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            onClickCell.onClickCell2(getAdapterPosition());
        }
    }

    public interface OnClickCell2 {
        void onClickCell2(int positon);
    }

}