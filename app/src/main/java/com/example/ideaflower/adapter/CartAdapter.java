package com.example.ideaflower.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ideaflower.Homepage;
import com.example.ideaflower.R;
import com.example.ideaflower.cartFlower;
import com.example.ideaflower.classs.Cart;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> arrayCart;

    public CartAdapter(Context context, ArrayList<Cart> arrayCart) {
        this.context = context;
        this.arrayCart = arrayCart;
    }

    @Override
    public int getCount() {
        return arrayCart.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        public TextView tv_namecart,tv_pricecart;
        public ImageView img_cart;
        public Button btn_minus,btn_values,btn_plus;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder =new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.listorder,null);
            viewHolder.tv_namecart=view.findViewById(R.id.tv_namecart);
            viewHolder.tv_pricecart=view.findViewById(R.id.tv_pricecart);
            viewHolder.img_cart=view.findViewById(R.id.img_cart);
            viewHolder.btn_minus=view.findViewById(R.id.btn_minus);
            viewHolder.btn_values=view.findViewById(R.id.btn_values);
            viewHolder.btn_plus=view.findViewById(R.id.btn_plus);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }
        Cart cart=(Cart) getItem(i);
        viewHolder.tv_namecart.setText(cart.getNameflower());
        viewHolder.tv_pricecart.setText((int) cart.getPrice()+ " vnd");
        viewHolder.img_cart.setImageResource(cart.getImgflower());
        viewHolder.btn_values.setText(cart.getQuantity() +"");
        int sl=Integer.parseInt(viewHolder.btn_values.getText().toString());
        if(sl>=20){
            viewHolder.btn_plus.setVisibility(View.INVISIBLE);
            viewHolder.btn_minus.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btn_plus.setVisibility(View.VISIBLE);
            viewHolder.btn_minus.setVisibility(View.INVISIBLE);
        }else if(sl>=1) {
            viewHolder.btn_plus.setVisibility(View.VISIBLE);
            viewHolder.btn_minus.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi=Integer.parseInt(finalViewHolder.btn_values.getText().toString()+1);
                int slhientai= Homepage.mListCart.get(i).getQuantity();
                int giaht= (int) Homepage.mListCart.get(i).getPrice();
                Homepage.mListCart.get(i).setQuantity(slmoi);
                //tisnh gia tien moi
                int giamoi=(giaht*slmoi)/slhientai;
                Homepage.mListCart.get(i).setPrice(giamoi);
                finalViewHolder.tv_pricecart.setText(giamoi+ " vnd");
                cartFlower.setData();
                if(slmoi>19){
                    finalViewHolder.btn_plus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_values.setText(String.valueOf(slmoi));
                }else{
                    finalViewHolder.btn_plus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_values.setText(String.valueOf(slmoi));
                }
            }
        });
        ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi=Integer.parseInt(finalViewHolder1.btn_values.getText().toString())-1;
                int slhientai= Homepage.mListCart.get(i).getQuantity();
                int giaht= (int) Homepage.mListCart.get(i).getPrice();
                Homepage.mListCart.get(i).setQuantity(slmoi);
                //tisnh gia tien moi
                int giamoi=(giaht*slmoi)/slhientai;
                Homepage.mListCart.get(i).setPrice(giamoi);
                finalViewHolder.tv_pricecart.setText(giamoi+ " vnd");
                cartFlower.setData();
                if(slmoi<2){
                    finalViewHolder.btn_plus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_values.setText(String.valueOf(slmoi));
                }else{
                    finalViewHolder.btn_plus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_values.setText(String.valueOf(slmoi));
                }
            }
        });
        return view;
    }
}
