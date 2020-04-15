package com.simplelifestudio.letscook1.adapters;

import android.content.Context;
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

import java.util.List;

public class AdapterDireccion extends RecyclerView.Adapter<AdapterDireccion.viewHolder> {
    private List<String> paso;
    private List<String> direccion;
    private int icono;
    private LayoutInflater layoutInflater;
    private AdapterView.OnItemClickListener onItemClickListener;
    private Context context;

    public AdapterDireccion(Context context, List paso, List direccion, int icono) {
      this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.paso = paso;
        this.direccion = direccion;
        this.icono = icono;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.celda_direccion,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    String paso;
    String direccion;
    int icono;

    }

    @Override
    public int getItemCount() {
        return paso.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView paso;
        TextView direccion;
        TextView vervideo;
        ImageView ico;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            paso = itemView.findViewById(R.id.celdaDireccionPasoTC);
            direccion = itemView.findViewById(R.id.celdaDireccionInfoTv);
            ico = itemView.findViewById(R.id.celda_direccionIcoIV);
            vervideo = itemView.findViewById(R.id.celdaDireccionVerVideoTv);
            vervideo.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.w("EVENTO","boton ver video presionado");
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
