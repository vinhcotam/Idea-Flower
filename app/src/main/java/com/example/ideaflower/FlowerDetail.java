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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideaflower.adapter.OrderAdapter;
import com.example.ideaflower.adapter.flowerAdapter;
import com.example.ideaflower.adapter.voteAdapter;
import com.example.ideaflower.classs.Cart;
import com.example.ideaflower.classs.Flower;
import com.example.ideaflower.classs.Order;
import com.example.ideaflower.classs.vote;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FlowerDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String email="";
    ImageButton imgBT_cart;
    private int mSelectId;
    private  ArrayList<vote> mListVote;
    private ArrayList<Flower> mListFlower;
    TextView tv_name,tv_price,tv_quantity,tv_namesptt,tv_pricesptt,
            tv_category,tv_color;
    Button bt_addtocart,bt_vote;
    ImageView img_chitiet,img_sptt;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar,tb_cart;
    RecyclerView rcv_vote,rcv_flower;
    voteAdapter voteAdapter;
    com.example.ideaflower.adapter.flowerAdapter flowerAdapter;
    RatingBar rating1,rating;
    EditText et_namevote,et_contentvote;
    String flowerid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);
        anhXa();
        ConnectDB();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         flowerid = bundle.getString("flowerid");
          email=bundle.getString("email");
        mListVote=new ArrayList<>();
        mListVote=displayDataVote();
        mListFlower=new ArrayList<>();
        mListFlower=displayDataFlower();
//        insertData();
        loadDataChitietSP();
        displayDataVote();
        setDataVote();
        setClickVote();
        displayDataFlower();
        setDataFlower();
        setClickAddtoCart();
        setClickCartImgButton();
    }

    private void setClickCartImgButton() {
        imgBT_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FlowerDetail.this, cartFlower.class);
                Bundle bundle=new Bundle();
                bundle.putString("email",email);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    private void setClickAddtoCart() {

        bt_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lay so luong
                if(Homepage.mListCart.size()>0){
                    int sl=1;
                    boolean exit=false;
                    for(int i=0;i<Homepage.mListCart.size();i++){
                        if(Homepage.mListCart.get(i).getIdflower()==idflower){
                            Homepage.mListCart.get(i).setQuantity(Homepage.mListCart.get(i).getQuantity()+sl);
                            if(Homepage.mListCart.get(i).getQuantity()>=20){
                                Homepage.mListCart.get(i).setQuantity(20);
                            }
                            Homepage.mListCart.get(i).setPrice(price*Homepage.mListCart.get(i).getQuantity());
                            exit=true;
                        }
                    }
                    if(exit==false){
                        sl=1;
                        int giamoi=sl*price;
                        Homepage.mListCart.add(new Cart(idflower,nameflower,giamoi,imgflower,sl));
                    }
                }else{
                    int sl=1;
                    int giamoi=sl*price;
                    Homepage.mListCart.add(new Cart(idflower,nameflower,giamoi,imgflower,sl));

                }
//                Intent intent=new Intent(FlowerDetail.this,cartFlower.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_LONG).show();

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
        String category=  tv_category.getText().toString();
        String sql="Select * from Flower where category='"+category+"'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Flower>mListFlower=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Flower flower=new Flower();
                String idflower1=cursor.getString(0);
                String nameflower1=cursor.getString(1);
                int price1=cursor.getInt(3);
                int imgflower1=cursor.getInt(5);
                flower.setFlowername(nameflower1);
                flower.setFlowerid(idflower1);
                flower.setPrice(price1);
                flower.setImgid(imgflower1);
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
                    String sql="Insert into Vote(email,content,numstar,idflower) values ('"+name+"','"+content+"',"+ratingvote+",'"+flowerid+"')";
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
        String sql="Select * from Vote where idflower='"+flowerid+"'  limit 5";
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
//        tv_motasp=findViewById(R.id.tv_motasp);
        bt_addtocart=findViewById(R.id.bt_addtocart);
        navigationView=findViewById(R.id.navigationview);
        tv_quantity=findViewById(R.id.tv_quantity);
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
        tv_category=findViewById(R.id.tv_category);
        tv_color=findViewById(R.id.tv_color);
        imgBT_cart=findViewById(R.id.imgBT_cart);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    SQLiteDatabase db = null;
    void ConnectDB(){
        db=openOrCreateDatabase("IdeaFlower.db", MODE_PRIVATE, null);
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
//        String sql1="Insert into Vote values('test','test',1,'flower2_date')"
//                +",('test1','test1',2,'flower2_date')"
//                +",('test2','test2',3,'flower2_date')";
////        db.execSQL(sql);
//        db.execSQL(sql1);
                String sql1="Insert into Vote values('test','test',1,'lver')"
                +",('test1','test1',2,'lver')"
                +",('test2','test2',3,'lver')";
//        db.execSQL(sql);
        db.execSQL(sql1);
    }
    String idflower,nameflower,catagory,color,content;
    int price,quantity,imgflower;
    float ratingvote;
    void loadDataChitietSP() {
        String sql = "Select * from Flower where idflower= '"+flowerid+"'";
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
            tv_category.setText(catagory);
            tv_color.setText(color);
//            tv_motasp.setText("Phân loại hoa: " + catagory + " " + " màu sắc: " + color);
            //lấy ảnh từ db, chuyển về dạng int
            img_chitiet.setImageResource(imgflower);
            cursor.moveToNext();
        }
        cursor.close();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent=null;
        item.setChecked(true);
        mSelectId=item.getItemId();
        if(item.getItemId()==R.id.nav_home) {
            drawerLayout.closeDrawer(GravityCompat.START);
            intent=new Intent(FlowerDetail.this,Homepage.class);
            Bundle bundle=new Bundle();
            bundle.putString("email",email);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }else if(item.getItemId()==R.id.nav_cart){
            drawerLayout.closeDrawer(GravityCompat.START);
            intent=new Intent(FlowerDetail.this,cartFlower.class);
            Bundle bundle=new Bundle();
            bundle.putString("email",email);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
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

}