package com.example.ideaflower;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        deleteCart();
        setClickTieptuc();
    }

    private void setClickTieptuc() {
        bt_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cartFlower.this,Homepage.class);
                startActivity(intent);
            }
        });
    }
    private void setClickThanhToan(){
        bt_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void deleteCart() {
        lv_cart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(cartFlower.this);
                builder.setTitle("Xác nhận xóa hoa ra khỏi giỏ hàng");
                builder.setMessage("Xóa");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(Homepage.mListCart.size()<=0){
                            tv_thongbao.setVisibility(View.VISIBLE);
                        }else{
                            Homepage.mListCart.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            setData();
                            if(Homepage.mListCart.size()<=0){
                                tv_thongbao.setVisibility(View.VISIBLE);
                            }else{
                                tv_thongbao.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                setData();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.notifyDataSetChanged();
                        setData();
                    }
                });
                builder.show();
                return true;
            }
        });
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