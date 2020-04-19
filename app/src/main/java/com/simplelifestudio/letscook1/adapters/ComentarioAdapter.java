package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Comentarios;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.viewHolder> {
    private ArrayList<Comentarios> comentarios;
    private LayoutInflater layoutInflater;
    private Context context;

    public ComentarioAdapter(Context context,ArrayList<Comentarios> comentarios) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.celda_comentario,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nombre.setText(comentarios.get(position).getUsuarioNombre());
        holder.usuarioPerfil.setImageResource(Integer.valueOf(comentarios.get(position).getUsuarioPerfil()));
        holder.comentario.setText(comentarios.get(position).getComentario());
        holder.comentarioID = comentarios.get(position).getComentarioId();
        holder.usuarioID = comentarios.get(position).getUsuarioID();
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView nombre;
        TextView comentario;
        CircleImageView usuarioPerfil;
        String comentarioID;
        String usuarioID;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.celdaComentarioNombreTV);
            comentario = itemView.findViewById(R.id.celdaComentarioComentarioTV);
            usuarioPerfil = itemView.findViewById(R.id.celdaComentarioPerfilCV);
            comentarioID = "";
            usuarioID = "";
        }

        @Override
        public void onClick(View view) {

        }
    }
}
