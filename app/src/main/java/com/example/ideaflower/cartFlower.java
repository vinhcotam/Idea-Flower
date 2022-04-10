package com.example.ideaflower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ideaflower.adapter.CartAdapter;

public class cartFlower extends AppCompatActivity {
    ListView lv_cart;
    TextView tv_thongbao;
    static TextView tv_total;
    Button bt_thanhtoan,bt_tieptuc;
    Toolbar tb_cart;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_flower);
        anhXa();
        ActionToolBar();
        CheckData();
        setData();
    }

    public static void setData() {
        int total=0;
        for(int i=0;i<Homepage.mListCart.size();i++){
            total+=Homepage.mListCart.get(i).getPrice();
        }
        tv_total.setText(total+ " vnd");
    }

    private void CheckData() {
        if(Homepage.mListCart.size()<=0){
            cartAdapter.notifyDataSetChanged();
            tv_thongbao.setVisibility(View.VISIBLE);
            lv_cart.setVisibility(View.INVISIBLE);
        }else{
            cartAdapter.notifyDataSetChanged();
            tv_thongbao.setVisibility(View.INVISIBLE);
            lv_cart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(tb_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb_cart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void anhXa() {
        lv_cart=findViewById(R.id.lv_cart);
        tv_thongbao=findViewById(R.id.tv_thongbao);
        tv_total=findViewById(R.id.tv_total);
        bt_thanhtoan=findViewById(R.id.bt_thanhtoan);
        bt_tieptuc=findViewById(R.id.bt_tieptuc);
        tb_cart=findViewById(R.id.tb_cart);
        cartAdapter=new CartAdapter(cartFlower.this,Homepage.mListCart);
        lv_cart.setAdapter(cartAdapter);
    }
}