package com.example.ideaflower.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaflower.R;
import com.example.ideaflower.classs.Flower;

import java.util.ArrayList;

public class flowerAdapter extends RecyclerView.Adapter<flowerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Flower> mListFlower;
    public flowerAdapter(Context context, ArrayList<Flower> mListFlower) {
        this.context = context;
        this.mListFlower = mListFlower;
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
        String idflower=Flower.getFlowerid();
        float price=mListFlower.get(position).getPrice();
        int imgflower=Flower.getImgid();
        holder.tv_namesptt.setText(nameflower);
        holder.tv_idsptt.setText(idflower);
        holder.tv_pricesptt.setText(price+ " vnđ");
        holder.img_sptt.setImageResource(imgflower);
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
            tv_idsptt=itemView.findViewById(R.id.tv_idsptt);
        }
    }
}
