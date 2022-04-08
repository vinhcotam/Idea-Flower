package com.example.ideaflower.adapter;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ContentInfoCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaflower.FlowerDetail;
import com.example.ideaflower.Homepage;
import com.example.ideaflower.R;
import com.example.ideaflower.Signup;
import com.example.ideaflower.classs.Flower;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
    String email;
    ArrayList<Flower> flowers;
    Context context;
    public ContentAdapter(ArrayList<Flower> flowers, String email, Context context) {
        this.flowers = flowers;
        this.context = context;
        this.email = email;
    }

    @NonNull
    @Override
    public ContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.fragment_load_content_home, parent, false);
        return new ContentHolder(itemview);
    }

    int index;
    @Override
    public void onBindViewHolder(@NonNull ContentHolder holder, int position) {

        index = holder.getAdapterPosition();
        holder.imgv.setImageResource(flowers.get(index*2).getImgid());
        holder.tvname.setText(flowers.get(index*2).getFlowername());
        holder.tvprice.setText(String.valueOf(flowers.get(index*2).getPrice()) + "đ");
        holder.imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FlowerDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("flowerid", flowers.get(index*2).getFlowerid());
                bundle.putString("flowername", flowers.get(index*2).getFlowername());
                bundle.putString("email", email);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.tvname.setText("dauma");
                context.startActivity(intent);
            }
        });
        if(position*2+1 == flowers.size()){
            holder.lay2.setVisibility(View.INVISIBLE);
            return;
        }
        holder.imgv2.setImageResource(flowers.get(index*2+1).getImgid());
        holder.tvname2.setText(flowers.get(index*2+1).getFlowername());
        holder.tvprice2.setText(String.valueOf(flowers.get(index*2+1).getPrice()) +"đ");
        holder.imgv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FlowerDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("flowerid", flowers.get(index*2+1).getFlowerid());
                bundle.putString("flowername", flowers.get(index*2+1).getFlowername());
                bundle.putString("email", email);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
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

