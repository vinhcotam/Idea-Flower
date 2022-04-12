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
import com.example.ideaflower.classs.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    String email;
    private ArrayList<Order> mListOrder;

    public OrderAdapter(Context context, String email, ArrayList<Order> mListOrder) {
        this.context = context;
        this.email = email;
        this.mListOrder = mListOrder;
    }


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemorder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order=mListOrder.get(position);
        int img=order.getImg();
        String dc=order.getLocation();
        String name=order.getNameflower();
        int quan=order.getQuantity();
        int price=order.getPrice();
        int sdt=order.getPhone();
//        holder.tv_giasp.setText(price);
        holder.img_order.setImageResource(img);
        holder.tv_quantitysp.setText(""+quan);
        holder.tv_tensp.setText(name);
        holder.tv_giasp.setText(""+price);
//        holder.tv_tensp.setText(name);


    }

    @Override
    public int getItemCount() {
        return mListOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_order;
        TextView tv_tensp,tv_quantitysp,tv_giasp,tv_dc,tv_sdt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_order=itemView.findViewById(R.id.img_order);
            tv_dc=itemView.findViewById(R.id.tv_dc);
            tv_tensp=itemView.findViewById(R.id.tv_tensp);
            tv_quantitysp=itemView.findViewById(R.id.tv_quantitysp);
            tv_giasp= itemView.findViewById(R.id.tv_giasp);
            tv_sdt=itemView.findViewById(R.id.tv_sdt);
        }
    }
}
