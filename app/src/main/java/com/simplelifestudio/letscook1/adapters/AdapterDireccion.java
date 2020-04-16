package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.controller.reproductor_horizontalActivity;
import com.simplelifestudio.letscook1.model.Paso;

import java.util.ArrayList;


public class AdapterDireccion extends RecyclerView.Adapter<AdapterDireccion.viewHolder> {
    private ArrayList<Paso> paso;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterDireccion(Context context, ArrayList paso) {
      this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.paso = paso;
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
    holder.icono.setImageResource(paso.get(position).getIco());
    holder.posVideo = paso.get(position).getPosVideo();
    holder.videoUrl = paso.get(position).getVideoUrl();
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
        String videoUrl;
        int posVideo;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            numeroPaso = itemView.findViewById(R.id.celdaDireccionPasoTC);
            direccion = itemView.findViewById(R.id.celdaDireccionInfoTv);
            icono = itemView.findViewById(R.id.celda_direccionIcoIV);
            vervideo = itemView.findViewById(R.id.celdaDireccionVerVideoTv);
            vervideo.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {

            context.startActivity(new Intent(context, reproductor_horizontalActivity.class).putExtra("videoUrl",videoUrl).putExtra("posVideo",posVideo));
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
