package com.example.ideaflower.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaflower.R;
import com.example.ideaflower.classs.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Order> mListOrder;

    public OrderAdapter(Context mContext, ArrayList<Order> mListOrder) {
        this.mContext = mContext;
        this.mListOrder = mListOrder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listorder,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order=new Order();
        String name=mListOrder.get(position).getmFlowerName();
        int img=mListOrder.get(position).getImgFlower();
    }

    @Override
    public int getItemCount() {

        return mListOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nameflower;
        ImageView img_orderflower;
        NumberPicker np_quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            tv_nameflower=itemView.findViewById(R.id.tv_nameflower);
//            img_orderflower=itemView.findViewById(R.id.img_orderflower);
//            np_quantity=itemView.findViewById(R.id.np_quantity);
        }
    }
}
