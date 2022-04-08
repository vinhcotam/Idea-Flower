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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideaflower.adapter.OrderAdapter;
import com.example.ideaflower.adapter.flowerAdapter;
import com.example.ideaflower.adapter.voteAdapter;
import com.example.ideaflower.classs.Flower;
import com.example.ideaflower.classs.Order;
import com.example.ideaflower.classs.vote;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FlowerDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  static final int Fragment_home=0;
    private  static final int Fragment_cart=1;
    private  static final int Fragment_order=2;
    private int mCurrentFragment=Fragment_home;
    private  ArrayList<vote> mListVote;
    Spinner spinner;
    private ArrayList<Flower> mListFlower;
    TextView tv_name,tv_price,tv_motasp,tv_quantity,tv_namesptt,tv_pricesptt,tv_thongbao,tv_total;
    Button bt_addtocart,bt_vote,bt_thanhtoan,bt_tieptuc;
    ImageView img_chitiet,img_sptt;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar,tb_cart;
    RecyclerView rcv_vote,rcv_flower,rcv_order;
    voteAdapter voteAdapter;
    com.example.ideaflower.adapter.flowerAdapter flowerAdapter;
    RatingBar rating1,rating;
    EditText et_namevote,et_contentvote;
    OrderAdapter orderAdapter;
    public static ArrayList<Order> mListOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);
        anhXa();
        ConnectDB();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Toast.makeText(this, bundle.getString("flowerid"), Toast.LENGTH_LONG).show();
        mListVote=new ArrayList<>();
        mListVote=displayDataVote();
        mListFlower=new ArrayList<>();
        mListFlower=displayDataFlower();

        //        replacFragment(new home());
//        navigationView.getMenu().findItem(R.id.home).setCheckable(true);
        //insertData();
        loadDataChitietSP();
        displayDataVote();
        setDataVote();
        setClickVote();
        displayDataFlower();
        setDataFlower();
//        setClickAddtoCart();

    }

    private void setClickAddtoCart() {

        bt_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lay so luong

            }
        });
    }

    private void setDataFlower() {
        flowerAdapter =new flowerAdapter(FlowerDetail.this,mListFlower);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FlowerDetail.this,RecyclerView.HORIZONTAL,false);
        rcv_flower.setLayoutManager(linearLayoutManager);
        rcv_flower.setAdapter(flowerAdapter);
    }

    private ArrayList<Flower> displayDataFlower() {
        String sql="Select * from Flower where category='Date'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Flower>mListFlower=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Flower flower=new Flower();
                idflower=cursor.getString(0);
                nameflower=cursor.getString(1);
                price=cursor.getInt(3);
                imgflower=cursor.getInt(5);
                flower.setFlowername(nameflower);
                flower.setFlowerid(idflower);
                flower.setPrice(price);
                flower.setImgid(imgflower);
                mListFlower.add(flower);

            }while(cursor.moveToNext());

        }
        cursor.close();
        return mListFlower;
    }

    private void setClickVote() {
        bt_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_namevote.getText().toString().trim();
                String content=et_contentvote.getText().toString().trim();
                float ratingvote=rating.getRating();
                if(name==null||content==null||ratingvote==0){
                    Toast.makeText(FlowerDetail.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }else{
                    String sql="Insert into Vote(email,content,numstar,idflower) values ('"+name+"','"+content+"',"+ratingvote+",'flower2_date')";
                    db.execSQL(sql);
                    displayDataVote();
                    setDataVote();
//                    String sql1="Select * from Vote where idflower='flower2_date'";
//                    db.execSQL(sql1);
                    Toast.makeText(FlowerDetail.this,"Đánh giá thành công",Toast.LENGTH_LONG).show();
                    et_namevote.setText("");
                    et_contentvote.setText("");
                }
            }
        });
    }

    private ArrayList<vote> displayDataVote() {
        String sql="Select * from Vote where idflower='flower2_date'  limit 3";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<vote>mListVote=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                vote vote=new vote();
                email=cursor.getString(0);
                content=cursor.getString(1);
                ratingvote=cursor.getFloat(2);
                vote.setEmail(email);
                vote.setContent(content);
                vote.setNumStar(ratingvote);
                mListVote.add(vote);
            }while(cursor.moveToNext());

        }
        cursor.close();
        return mListVote;

    }
    private void setDataVote(){
        voteAdapter=new voteAdapter(mListVote,FlowerDetail.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FlowerDetail.this,RecyclerView.HORIZONTAL,false);
        rcv_vote.setLayoutManager(linearLayoutManager);
        rcv_vote.setAdapter(voteAdapter);
    }
    private void anhXa(){
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
        navigationView.setNavigationItemSelectedListener(this);
        rcv_vote=findViewById(R.id.rcv_vote);
        rating1=findViewById(R.id.rating1);
        rating=findViewById(R.id.rating);
        et_namevote=findViewById(R.id.et_namevote);
        et_contentvote=findViewById(R.id.et_contentvote);
        bt_vote=findViewById(R.id.bt_vote);
        tv_namesptt=findViewById(R.id.tv_namesptt);
        tv_pricesptt=findViewById(R.id.tv_namesptt);
        img_sptt=findViewById(R.id.img_sptt);
        rcv_flower=findViewById(R.id.rcv_flower);
        if(mListOrder!=null){

        }else{
            mListOrder=new ArrayList<>();
        }

    }
    SQLiteDatabase db = null;
    void ConnectDB(){
        db=openOrCreateDatabase("FlowerStore.db", MODE_PRIVATE, null);
        //tạo table nếu chưa có
//        String sql = "create table if not exists Flower(idflower char(50) primary key, nameflower char(50), category char(50),price int,color char(50),imgflower int,quantity int)";
        String sql1="create table if not exists Vote(email char(50),content char(100),numstar float,idflower char(50),foreign key (idflower)references Flower(idflower))";
//        db.execSQL(sql);
        db.execSQL(sql1);
//        String sql = "DROP table Flower ";
//        db.execSQL(sql);
//                String sql2 = "DROP table Vote ";
//        db.execSQL(sql2);
    }
    void insertData(){
        String sql="Insert into Flower values('flower1_date','First Date','Date',300000,'yellow and white',"+R.drawable.hoa1_date+",100)"
                + ",('flower2_date','Carla','Date',550000,'pink',"+R.drawable.hoa2_date+",100)"
                +",('flower3_date','La Vie En Rose','Date',500000,'pink and violet',"+R.drawable.hoa3_date+",100)"
                +",('flower4_date','Violet Lover','Date',700000,'pink and violet',"+R.drawable.hoa4_date+",100)";
//        String sql="Insert into Flower values('flower5_date','Firt Date','Date',300000,'yellow and white','hoa4_date')";
        String sql1="Insert into Vote values('test','test',1,'flower2_date')"
                +",('test1','test1',2,'flower2_date')"
                +",('test2','test2',3,'flower2_date')";
//        db.execSQL(sql);
        db.execSQL(sql1);
    }
    String idflower,nameflower,catagory,color,idvote,email,content;
    int price,quantity,imgflower;
    float ratingvote;
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
        cursor.close();
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