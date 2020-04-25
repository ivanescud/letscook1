package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.controller.receta_detailActivity;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRecetaAdapter extends RecyclerView.Adapter<HomeRecetaAdapter.viewHolder> {
    ArrayList<Receta> recetas;
    private Context context;
    LayoutInflater layoutInflater;
     OnClickCell onClickCell;

    public HomeRecetaAdapter(ArrayList<Receta> recetas, Context context, OnClickCell onClickCell) {
        this.recetas = recetas;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.onClickCell = onClickCell;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.homecellrv, parent, false);
        return new viewHolder(view,onClickCell);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Glide.with(context).load(recetas.get(position).getMainImgRc()).into(holder.mainImgIV);
        Glide.with(context).load(recetas.get(position).getAutorImgRC()).into(holder.createdCIV);
        holder.nombreRc.setText(recetas.get(position).getNombreRC());
        holder.likesRC.setText(String.valueOf(recetas.get(position).getLikes().size()));
        holder.rankRC.setText(String.valueOf(recetas.get(position).getRankingRC()));
        holder.timeTV.setText(recetas.get(position).getTiempo());


    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreRc;
        TextView likesRC;
        TextView rankRC;
        TextView timeTV;
        CircleImageView createdCIV;
        ImageView mainImgIV;

        OnClickCell onClickCell;

        public viewHolder(@NonNull View itemView, OnClickCell onClickCell) {
            super(itemView);
            mainImgIV = itemView.findViewById(R.id.homecellimg);
            nombreRc = itemView.findViewById(R.id.homecellTitleTV);
            likesRC = itemView.findViewById(R.id.homecelllikeTv);
            rankRC = itemView.findViewById(R.id.homecellrankTV);
            createdCIV = itemView.findViewById(R.id.homecellbyImgCIV);
            timeTV = itemView.findViewById(R.id.homecelltimeTV);

            this.onClickCell = onClickCell;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onClickCell.onClickCell(getAdapterPosition());
        }
    }

    public interface OnClickCell {
        void onClickCell(int positon);
    }
}
