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

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeBebidasAdapter extends RecyclerView.Adapter<HomeBebidasAdapter.viewHolder> {
    ArrayList<Receta> recetas;
    private Context context;
    LayoutInflater layoutInflater;
    OnClickCell2 onClickCell;

    public HomeBebidasAdapter(ArrayList<Receta> recetas, Context context, OnClickCell2 onClickCell) {
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


    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreRc;
        TextView likesRC;
        TextView rankRC;
        CircleImageView createdCIV;
        ImageView mainImgIV;

        OnClickCell2 onClickCell;

        public viewHolder(@NonNull View itemView, OnClickCell2 onClickCell) {
            super(itemView);
            mainImgIV = itemView.findViewById(R.id.homecellimg);
            nombreRc = itemView.findViewById(R.id.homecellTitleTV);
            likesRC = itemView.findViewById(R.id.homecelllikeTv);
            rankRC = itemView.findViewById(R.id.homecellrankTV);
            createdCIV = itemView.findViewById(R.id.homecellbyImgCIV);

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

