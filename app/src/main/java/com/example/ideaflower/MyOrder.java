package com.example.ideaflower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ideaflower.adapter.OrderAdapter;
import com.example.ideaflower.adapter.flowerAdapter;
import com.example.ideaflower.classs.Flower;
import com.example.ideaflower.classs.Order;

import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {
    ImageView img_order;
    TextView tv_tensp,tv_quantitysp,tv_giasp,tv_dc,tv_sdt;
    SQLiteDatabase db;
    RecyclerView rcv_order;
    String email;
    ArrayList<Order> mListOrder;
    OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        email=bundle.getString("email");
        anhXa();
        connectDB();
        mListOrder=new ArrayList<>();
        mListOrder=displayDataOrder();
        setDataOrder();
    }
    private void setDataOrder() {
        orderAdapter =new OrderAdapter(MyOrder.this,email,mListOrder);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyOrder.this,RecyclerView.VERTICAL,false);
        rcv_order.setLayoutManager(linearLayoutManager);
        rcv_order.setAdapter(orderAdapter);
    }
    private ArrayList<Order> displayDataOrder() {
//        String sql="Select Order1.phone,Order1.location,DetailOrder.id,DetailOrder.nameflower,DetailOrder.price,DetailOrder.quantity" +
//                "DetailOrder.imgflower where DetailOrder.Email='"+email+"' " +
//                "and DetailOrder.Email=Order1.Email";

        String sql1 = "Select * from DetailOrder where Email='" + email + "'";
        Cursor cursor = db.rawQuery(sql1, null);
        ArrayList<Order> mListOrder = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
//                int phone=cursor.getInt(0);
//                String location=cursor.getString(1);
//                String id=cursor.getString(2);
//                String name=cursor.getString(3);
//                int pricee=cursor.getInt(4);
//                int quan=cursor.getInt(5);
//                int img=cursor.getInt(6);
//                order.setIdorder(id);
//                order.setImg(img);
//                order.setLocation(location);
//                order.setNameflower(name);
//                order.setPhone(phone);
//                order.setEmail(email);
//                order.setQuantity(quan);
//                order.setPrice(pricee);
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                int price = cursor.getInt(2);
                int quan = cursor.getInt(3);
                int img = cursor.getInt(4);
                order.setNameflower(name);
                order.setImg(img);
                order.setQuantity(quan);
                order.setPrice(price);
                mListOrder.add(order);
            } while (cursor.moveToNext());
        }cursor.close();
        return mListOrder;

    }

    private void connectDB() {
        db=openOrCreateDatabase("IdeaFlower.db", MODE_PRIVATE, null);
    }

    private void anhXa() {
        img_order=findViewById(R.id.img_order);
        tv_tensp=findViewById(R.id.tv_tensp);
        tv_quantitysp=findViewById(R.id.tv_quantitysp);
        tv_giasp=findViewById(R.id.tv_giasp);
        tv_dc=findViewById(R.id.tv_dc);
        tv_sdt=findViewById(R.id.tv_sdt);
        rcv_order=findViewById(R.id.rcv_order);
    }
}