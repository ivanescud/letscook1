package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.controller.reproductor_horizontalActivity;
import com.simplelifestudio.letscook1.model.Paso;

import java.util.ArrayList;


public class AdapterDireccion extends RecyclerView.Adapter<AdapterDireccion.viewHolder> {
    private ArrayList<Paso> paso;
    private String videoUrl;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterDireccion(Context context, ArrayList paso,String url) {
      this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.paso = paso;
        this.videoUrl = url;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.celda_direccion,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.numeroPaso.setText(paso.get(position).getNumeroPaso());
    holder.direccion.setText((paso.get(position).getIntruccion()));
        Glide.with(context).load(paso.get(position).getIco());
    holder.posVideo = paso.get(position).getPosVideo();
    }

    @Override
    public int getItemCount() {
        return paso.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView numeroPaso;
        TextView direccion;
        TextView vervideo;
        ImageView icono;
        int posVideo;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            numeroPaso = itemView.findViewById(R.id.celdaDireccionPasoTV);
            direccion = itemView.findViewById(R.id.celdaDireccionInfoTv);
            icono = itemView.findViewById(R.id.celda_direccionIcoIV);
            vervideo = itemView.findViewById(R.id.celdaDireccionVerVideoTv);
            vervideo.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {

            context.startActivity(new Intent(context, reproductor_horizontalActivity.class));
            Bundle bundle = new Bundle();
            bundle.putString("videoUrl",videoUrl);
            bundle.putInt("posVideo",posVideo);
            bundle.putSerializable("paso",paso);
            Intent intent = new Intent(context,reproductor_horizontalActivity.class).putExtras(bundle);
            context.startActivity(intent);

    }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
