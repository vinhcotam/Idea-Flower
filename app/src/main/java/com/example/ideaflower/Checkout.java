package com.example.ideaflower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Random;

public class Checkout extends AppCompatActivity {
    EditText et_namepay,et_phonepay,et_locationpay;
    SQLiteDatabase db;
    Button bt_pay,btn_tiep;
    String email;
    int total;
    int id,price,imgflower,quantity;
    String nameflower;
    TextView tv_totalpay,tv_getemail;
    String idflower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        email=bundle.getString("email");
        total=bundle.getInt("total");
        anhXa();
        connectDB();
        setClickPay();
        setClickTieptuc();
    }

    private void setClickTieptuc() {
        btn_tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Checkout.this,Homepage.class);
                Bundle bundle=new Bundle();
                bundle.putString("email",email);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setClickPay() {
        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_namepay.getText().toString().trim();
                String phone= (et_phonepay.getText().toString().trim());
                String location=et_locationpay.getText().toString().trim();
                int phone1= Integer.parseInt(phone);
                Random random=new Random();
                id=random.nextInt();
                if(name.length()<=0||location.length()<=0||phone.length()<=0||phone.length()>11){
                    Toast.makeText(getApplicationContext(),"Vui l??ng nh???p l???i th??ng tin",Toast.LENGTH_LONG).show();
                }else{
                    for(int i=0;i<Homepage.mListCart.size();i++){
                         idflower=Homepage.mListCart.get(i).getIdflower();
                         nameflower=Homepage.mListCart.get(i).getNameflower();
                         price= (int) Homepage.mListCart.get(i).getPrice();
                         imgflower=Homepage.mListCart.get(i).getImgflower();
                         quantity=Homepage.mListCart.get(i).getQuantity();
                        String sql1="Insert into DetailOrder values("+id+",'"+nameflower+"',"+price+","+quantity+","+imgflower+",'"+email+"','"+name+"'," +
                                ""+phone1+",'"+location+"')";
                        db.execSQL(sql1);
                    }

                        Toast.makeText(getApplicationContext(),"?????t h??ng th??nh c??ng",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void connectDB() {
        db=openOrCreateDatabase("FlowerStoreDB.db", MODE_PRIVATE, null);

    }


    private void anhXa() {
        et_locationpay=findViewById(R.id.et_locationpay);
        et_namepay=findViewById(R.id.et_namepay);
        et_phonepay=findViewById(R.id.et_phonepay);
        bt_pay=findViewById(R.id.bt_pay);
        tv_totalpay=findViewById(R.id.tv_totalpay);
        tv_totalpay.setText(""+total);
        btn_tiep=findViewById(R.id.btn_tiep);
    }
}