package com.example.ideaflower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {
    EditText et_namepay,et_phonepay,et_locationpay;
    SQLiteDatabase db;
    Button bt_pay;
    String email;
    int total;
    TextView tv_totalpay;
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
////        insertDB();
//        setClickPay();
    }

    private void setClickPay() {
        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_namepay.getText().toString().trim();
                String phone= (et_phonepay.getText().toString().trim());
                String location=et_locationpay.getText().toString().trim();
                if(name.length()<=0||location.length()<=0||phone.length()<=0||phone.length()>11){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập lại thông tin",Toast.LENGTH_LONG).show();
                }else{
                    int phonenum= Integer.parseInt(phone);
                    String sql="Insert into OrderTable(namecus,phone,location) values('"+name+"',"+phonenum+",'"+location+"')";
                    db.execSQL(sql);
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập lại thông tin",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void connectDB() {
        db=openOrCreateDatabase("IdeaFlower.db", MODE_PRIVATE, null);
        String sql="Create table if not exists OrderTable(idorder int primary key AUTOINCREMENT,namecus char(50),phone int(11),location char(50))";
        db.execSQL(sql);
    }
    private void insertDB(){
        String sql="Insert into OrderTable(namecus,phone,location) values('v',01111,'test')";
        db.execSQL(sql);
    }

    private void anhXa() {
        et_locationpay=findViewById(R.id.et_locationpay);
        et_namepay=findViewById(R.id.et_namepay);
        et_phonepay=findViewById(R.id.et_phonepay);
        bt_pay=findViewById(R.id.bt_pay);
        tv_totalpay=findViewById(R.id.tv_totalpay);
        tv_totalpay.setText(""+total);
    }
}