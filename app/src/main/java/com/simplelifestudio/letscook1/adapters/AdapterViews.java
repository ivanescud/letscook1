package com.simplelifestudio.letscook1.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;


public class AdapterViews extends PagerAdapter {

    ArrayList<Receta> data;
    Activity activity;
    public static int LOOPS_COUNT = 1000;

    public AdapterViews(ArrayList<Receta> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if (data != null && data.size() > 0)
        {
            return data.size()*LOOPS_COUNT; // simulate infinite by big number of products
        }
        else
        {
            return 1;
        }


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }
    private int pos = 0;

    @Override
    public Object instantiateItem(View container, int position) {

        ViewHolder2 holder;
        View view = container;
        if (view==null || view.getTag()==null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.homecellrv, null);
             holder = new ViewHolder2();
            holder.bannerimg = view.findViewById(R.id.homecellimg);
            holder.title = view.findViewById(R.id.homecellTitleTV);
            holder.likes = view.findViewById(R.id.homecelllikeTv);
            holder.ranking = view.findViewById(R.id.homecellrankTV);



            view.setTag(holder);
        }else {

            holder = (ViewHolder2)view.getTag();

        }

        position = position % data.size();
        holder.receta = data.get(position);
        if (holder.receta.getMainImgRc().isEmpty()){
            Glide.with(activity).load(R.drawable.recetaholder).into(holder.bannerimg);
        }else {
            Glide.with(activity).load(holder.receta.getMainImgRc()).into(holder.bannerimg);
            holder.title.setText(holder.receta.getNombreRC().toString());
            holder.likes.setText(String.valueOf(holder.receta.getLikes().size()));
        }

        holder.bannerimg.setScaleType(ImageView.ScaleType.FIT_XY);
        ((ViewPager) container).addView(view);
        if (pos >= data.size() - 1)
            pos = 0;
        else
            ++pos;

        final ViewHolder2  holder2 = holder;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClass(activity, CuponDetail.class);
//                Bundle mBundle = new Bundle();
//                mBundle.putSerializable("pasedato", holder2.banner);
//                i.putExtras(mBundle);
//                activity.startActivity(i);
            }
        });



        return view;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public class ViewHolder2{
       Receta receta;
        TextView title;
        ImageView bannerimg;
        TextView likes;
        TextView ranking;


    }
}
