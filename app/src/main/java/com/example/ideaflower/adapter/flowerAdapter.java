package com.example.ideaflower.adapter;

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

import com.example.ideaflower.FlowerDetail;
import com.example.ideaflower.R;
import com.example.ideaflower.classs.Flower;

import java.util.ArrayList;

public class flowerAdapter extends RecyclerView.Adapter<flowerAdapter.ViewHolder> {
    private Context context;
    String email;
    private ArrayList<Flower> mListFlower;
    public flowerAdapter(Context context, ArrayList<Flower> mListFlower,String email) {
        this.context = context;
        this.mListFlower = mListFlower;
        this.email=email;
    }


    @NonNull
    @Override
    public flowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flower,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull flowerAdapter.ViewHolder holder, int position) {
        Flower Flower=mListFlower.get(position);
        String nameflower=Flower.getFlowername();
        float price=mListFlower.get(position).getPrice();
        int imgflower=Flower.getImgid();
        holder.tv_namesptt.setText(nameflower);
        holder.tv_pricesptt.setText(price+ " vnđ");
        holder.img_sptt.setImageResource(imgflower);
        holder.img_sptt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FlowerDetail.class);
                Bundle bundle=new Bundle();
                bundle.putString("flowerid",mListFlower.get(position).getFlowerid());
                bundle.putString("email",email);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
//        Glide.with(context).load(imgflower).into(img_sptt);
//        holder.img_sptt.setImageResource(2131165286);
    }

    @Override
    public int getItemCount() {
        return mListFlower.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_namesptt,tv_pricesptt,tv_idsptt;
        private ImageView img_sptt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namesptt=itemView.findViewById(R.id.tv_namesptt);
            img_sptt=itemView.findViewById(R.id.img_sptt);
            tv_pricesptt=itemView.findViewById(R.id.tv_pricesptt);
        }
    }
}
