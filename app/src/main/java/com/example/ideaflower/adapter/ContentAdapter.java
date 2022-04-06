package com.example.ideaflower.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaflower.R;
import com.example.ideaflower.classs.Flower;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
    ArrayList<Flower> flowers;
    Context context;

    public ContentAdapter(ArrayList<Flower> flowers, Context context) {
        this.flowers = flowers;
        this.context = context;
    }

    @NonNull
    @Override
    public ContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.fragment_load_content_home, parent, false);
        return new ContentHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentHolder holder, int position) {

        holder.imgv.setImageResource(flowers.get(position*2).getImgid());
        holder.tvname.setText(flowers.get(position*2).getFlowername());
        //đang lỗi ep kiểu
        //holder.tvprice.setText((int)flowers.get(position).getPrice());
        //holder.ratingb.setRating(flowers.get(position).getRating());
        if(position*2+1 == flowers.size()){
            holder.lay2.setVisibility(View.INVISIBLE);
            return;
        }
        holder.imgv2.setImageResource(flowers.get(position*2+1).getImgid());
        holder.tvname2.setText(flowers.get(position*2+1).getFlowername());
        //đang lỗi ep kiểu
        //holder.tvprice.setText((int)flowers.get(position).getPrice());
        //holder.ratingb.setRating(flowers.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return flowers.size()/2 + flowers.size()%2;
    }

    class ContentHolder extends RecyclerView.ViewHolder{
        TextView tvname;
        TextView tvprice;
        ImageView imgv;
        RatingBar ratingb;
        TextView tvname2;
        TextView tvprice2;
        ImageView imgv2;
        RatingBar ratingb2;
        LinearLayout lay2;

        public ContentHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.TV_name1);
            tvprice = itemView.findViewById(R.id.TV_pricef1);
            imgv = itemView.findViewById(R.id.imgFlower1);
            ratingb = itemView.findViewById(R.id.rating1);
            tvname2 = itemView.findViewById(R.id.TV_name2);
            tvprice2 = itemView.findViewById(R.id.TV_pricef2);
            imgv2 = itemView.findViewById(R.id.imgFlower2);
            ratingb2 = itemView.findViewById(R.id.rating2);
            lay2 = itemView.findViewById(R.id.lay2);
        }
    }

}

