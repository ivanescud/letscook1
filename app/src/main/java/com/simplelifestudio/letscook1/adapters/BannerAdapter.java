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
import com.simplelifestudio.letscook1.model.Banner;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BannerAdapter  extends RecyclerView.Adapter<BannerAdapter.viewHolder> {
    ArrayList<Banner> bannerlist;
    private Context context;
    LayoutInflater layoutInflater;
    OnClickCellBanner onClickCellBanner;

    public BannerAdapter(ArrayList<Banner> bannerlist, Context context, OnClickCellBanner onClickCellBanner) {
        this.bannerlist = bannerlist;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.onClickCellBanner = onClickCellBanner;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cellbanner, parent, false);
        return new viewHolder(view,onClickCellBanner);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Glide.with(context).load(bannerlist.get(position).getBannerMainImg()).into(holder.mainImgIV);
        holder.bannerTitleTV.setText(bannerlist.get(position).getBannerTitle());
        holder.bannerSubTitleTV.setText(bannerlist.get(position).getBannersubTile());



    }

    @Override
    public int getItemCount() {
        return bannerlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bannerTitleTV;
        TextView bannerSubTitleTV;
        ImageView mainImgIV;

        OnClickCellBanner onClickCellBanner;

        public viewHolder(@NonNull View itemView, OnClickCellBanner onClickCellBanner) {
            super(itemView);
            mainImgIV = itemView.findViewById(R.id.cellbannerMainImg);
            bannerTitleTV = itemView.findViewById(R.id.cellbannertitleTV);
            bannerSubTitleTV = itemView.findViewById(R.id.cellbannerSubTitleTV);

            this.onClickCellBanner = onClickCellBanner;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onClickCellBanner.onClickCell(getAdapterPosition());
        }
    }


    public interface OnClickCellBanner {
        void onClickCell(int positon);
    }
}
