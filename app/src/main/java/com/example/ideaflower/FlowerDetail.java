package com.example.ideaflower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ideaflower.adapter.voteAdapter;
import com.example.ideaflower.classs.vote;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FlowerDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<vote> mListVote;
    TextView tv_name,tv_price,tv_motasp,tv_quantity;
    Button bt_addtocart;
    ImageView img_chitiet;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView rcv_vote;
    voteAdapter test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);
        anhXa();
        ConnectDB();
        mListVote=new ArrayList<>();
        mListVote=displayData();
        insertData();
        loadDataChitietSP();
        displayData();
        setData();
    }
    SQLiteDatabase db = null;
    String idflower,nameflower,catagory,color,idvote,email,content;
    int price,quantity,imgflower;
    private void anhXa() {
        img_chitiet=findViewById(R.id.img_chitiet);
        tv_name=findViewById(R.id.tv_name);
        tv_price=findViewById(R.id.tv_price);
        tv_motasp=findViewById(R.id.tv_motasp);
        bt_addtocart=findViewById(R.id.bt_addtocart);
        navigationView=findViewById(R.id.navigationview);
        tv_quantity=findViewById(R.id.tv_quantity);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
        rcv_vote=findViewById(R.id.rcv_vote);
        bt_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FlowerDetail.this,cartFlower.class);
                startActivity(intent);
            }
        });
    }
    void ConnectDB(){
        db=openOrCreateDatabase("FlowerStore.db", MODE_PRIVATE, null);
        //tạo table nếu chưa có
        String sql = "create table if not exists Flower(idflower char(50) primary key, nameflower char(50), category char(50),price int,color char(50),imgflower int,quantity int)";
        String sql1="create table if not exists Vote(email char(50),content char(100),idflower char(50),foreign key (idflower)references Flower(idflower))";

        db.execSQL(sql);
        db.execSQL(sql1);
//        String sql = "DROP table Flower ";
//        db.execSQL(sql);
//                String sql1 = "DROP table Vote ";
//        db.execSQL(sql1);
    }
    void insertData(){
        String sql="Insert into Flower values('flower1_date','First Date','Date',300000,'yellow and white',"+R.drawable.hoa2_date+",100)"
                + ",('flower2_date','Carla','Date',550000,'pink',"+R.drawable.hoa2_date+",100)"
                +",('flower3_date','La Vie En Rose','Date',500000,'pink and violet',"+R.drawable.hoa3_date+",100)"
                +",('flower4_date','Violet Lover','Date',700000,'pink and violet',"+R.drawable.hoa4_date+",100)";
//        String sql="Insert into Flower values('flower5_date','Firt Date','Date',300000,'yellow and white','hoa4_date')";
        String sql1="Insert into Vote values('test','test','flower2_date')"
                +",('test1','test1','flower2_date')"
                +",('test2','test2','flower2_date')";
//        db.execSQL(sql);
        db.execSQL(sql1);
    }
    void loadDataChitietSP() {
        String sql = "Select * from Flower where idflower='flower2_date'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            idflower = cursor.getString(0);
            nameflower = cursor.getString(1);
            catagory = cursor.getString(2);
            price = cursor.getInt(3);
            color = cursor.getString(4);
            imgflower = cursor.getInt(5);
            quantity=cursor.getInt(6);
            tv_price.append(" " + price + " vnđ");
            tv_name.append(nameflower);
            tv_quantity.append(" "+quantity);
            tv_motasp.setText("Phân loại hoa: " + catagory + " " + " màu sắc: " + color);
            //lấy ảnh từ db, chuyển về dạng int
            img_chitiet.setImageResource(imgflower);
            cursor.moveToNext();
        }
    }
    private ArrayList<vote> displayData() {
        String sql="Select * from Vote where idflower='flower2_date'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<vote>mListVote=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                vote vote=new vote();
                email=cursor.getString(0);
                content=cursor.getString(1);
                vote.setEmail(email);
                vote.setContent(content);
                mListVote.add(vote);
            }while(cursor.moveToNext());

        }
        cursor.close();

        return mListVote;
    }
    private void setData(){
        mListVote.add(new vote("test","test"));
        mListVote.add(new vote("test1","test1"));
        test=new voteAdapter(mListVote,FlowerDetail.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FlowerDetail.this,RecyclerView.VERTICAL,false);
        rcv_vote.setLayoutManager(linearLayoutManager);
        rcv_vote.setAdapter(test);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.home){
//            if(mCurrentFragment!=Fragment_home){
//                replacFragment(new home());
//                mCurrentFragment=Fragment_home;
//
//            }
        }else if(id==R.id.cart){

        }else if(id==R.id.order){

        }else if(id==R.id.logout){

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //xly an nut back tren dthoai
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void replacFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentframe,fragment);
        transaction.commit();

    }
}