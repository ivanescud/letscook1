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
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRecetaAdapter  extends RecyclerView.Adapter<HomeRecetaAdapter.viewHolder> {
    ArrayList<Receta>recetas;
    private LayoutInflater layoutInflater;
    private Context context;

    public HomeRecetaAdapter(ArrayList<Receta> recetas, LayoutInflater layoutInflater, Context context) {
        this.recetas = recetas;
        this.layoutInflater = layoutInflater;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.homecellrv,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {




     /*   holder.nombreRc.setText(ingrediente.get(position).getProducto());
        holder.likesRC.setText(ingrediente.get(position).getCantidad());
        holder.icono.setImageResource(ingrediente.get(position).getIco());*/
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView nombreRc;
        TextView likesRC;
        TextView rankRC;
        CircleImageView createdCIV;
        ImageView mainImgIV;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mainImgIV = itemView.findViewById(R.id.homecellimg);
            nombreRc = itemView.findViewById(R.id.homecellTitleTV);
            likesRC = itemView.findViewById(R.id.homecelllikeTv);
            rankRC = itemView.findViewById(R.id.homecellrankTV);
            createdCIV = itemView.findViewById(R.id.homecellbyImgCIV);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
