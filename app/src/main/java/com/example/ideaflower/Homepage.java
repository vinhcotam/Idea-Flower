package com.example.ideaflower;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideaflower.adapter.ContentAdapter;
import com.example.ideaflower.classs.Cart;
import com.example.ideaflower.classs.Flower;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    public  static ArrayList<Cart> mListCart;
    String flowerid;
    int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        email = bundle.getString("email");
        if(mListCart!=null){

        }else{
            mListCart=new ArrayList<>();
        }
        ViewPager2 vp2 = findViewById(R.id.vp2_title);
        //setContentView(R.layout.fragment_slide);
        vp2.setAdapter(
                new View2Apdapter(this)
        );
        //setContentView(R.layout.activity_homepage);
        db = openOrCreateDatabase("FlowerStoreDB.db", MODE_PRIVATE, null);
        getDataFlower();
        setEvent();

    }
    ArrayList<Flower> flowers = new ArrayList<Flower>();
    String email = "";
    SQLiteDatabase db = null;

    void getDataFlower() {
        String sql = "Create table if not exists Flower(idflower char(50) primary key, nameflower char(50), category char(50),price int,color char(50),imgflower int,quantity int)";
        db.execSQL(sql);
        sql = "select * from Flower";
        selectflower(sql);
    }

    boolean selectflower(String sql){
        page=0;
        Cursor cursor = db.rawQuery(sql, null);
        try {
            flowers = new ArrayList<>();
            while (!cursor.isLast()) {
                cursor.moveToNext();
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String category = cursor.getString(2);
                int price = cursor.getInt(3);
                String color = cursor.getString(4);
                int imgid = cursor.getInt(5);
                int quantity = cursor.getInt(6);
                Flower fl = new Flower(id, name, category, price, color, imgid, quantity);
                flowers.add(fl);
            }
            if(flowers.size()==0){
                return false;
            }
            LoadContent();
        } catch (Exception e) {
            NoContent();
            return false;
        }
        return true;
    }
    void setEvent()
    {
        EditText editText = findViewById(R.id.ET_SearchFlower);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && i == KeyEvent.KEYCODE_ENTER) {
                    page=0;
                    String tukhoa = editText.getText().toString();
                    String sql = "select * from Flower where nameflower like '%"+ tukhoa +"%'";
                    return selectflower(sql);
                }
                return false;
            }
        });
        imgBT_cartHome = findViewById(R.id.imgBT_cartHome);
        imgBT_cartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepage.this, cartFlower.class);
                Bundle bundle=new Bundle();
                bundle.putString("email",email);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        ImageButton imgc = findViewById(R.id.imgBT_cCay);
        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = " Select * from Flower where category like '%C??y%'";
                selectflower(sql);
            }
        });
        imgc = findViewById(R.id.imgBT_cAll);
        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = " Select * from Flower";
                selectflower(sql);
            }
        });
        imgc = findViewById(R.id.imgBT_cHoa);
        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = " Select * from Flower where category like '%Hoa%'";
                selectflower(sql);
            }
        });
        imgc = findViewById(R.id.imgBT_cChau);
        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = " Select * from Flower where category like '%Ch???u%'";
                selectflower(sql);
            }
        });
        Button button = findViewById(R.id.backpage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page>0){
                    page--;
                    LoadContent();
                }
                else{
                    Toast.makeText(Homepage.this, "S??? trang ???? kh??ng th??? th???p h??n ???????c n???a", Toast.LENGTH_LONG).show();
                }
            }
        });
        button = findViewById(R.id.nextpage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hi???n th??? t???i ??a 10 m???t h??ng tr??n 1 trang. Trang ?????u l?? 0(hi???n th??? tr??n ???ng d???ng l?? 1)
                if(page<flowers.size()/10){
                    page++;
                    LoadContent();
                }
                else{
                    Toast.makeText(Homepage.this, "S??? trang ???? kh??ng th??? cao h??n ???????c n???a", Toast.LENGTH_LONG).show();
                }
            }
        });
        button = findViewById(R.id.gotopage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = findViewById(R.id.inputpage);
                int p = Integer.parseInt(et.getText().toString())-1;
                if(p<=flowers.size()/10 && p>=0){
                    page=p;
                    LoadContent();
                }
                else{
                    Toast.makeText(Homepage.this, "???", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    ImageView imgv;
    TextView textname;
    TextView textprice;
    Button btnaddcart;
    RatingBar rbar;
    LinearLayout lay;
    ImageButton imgBT_cartHome;
    LinearLayout NoContent;
    TableLayout HaveContent;
    void NoContent(){
        NoContent = findViewById(R.id.NoContent);
        NoContent.setVisibility(View.VISIBLE);
        HaveContent = findViewById(R.id.HaveContent);
        HaveContent.setVisibility(View.GONE);
    }
    void LoadContent() {

        NoContent = findViewById(R.id.NoContent);
        NoContent.setVisibility(View.GONE);
        HaveContent = findViewById(R.id.HaveContent);
        HaveContent.setVisibility(View.VISIBLE);

        EditText et = findViewById(R.id.inputpage);
        et.setText(""+(page+1));
        imgv = findViewById(R.id.imgFlower1);
        textname = findViewById(R.id.TV_name1);
        textprice = findViewById(R.id.TV_pricef1);
        btnaddcart = findViewById(R.id.BT_add1);
        rbar = findViewById(R.id.rating1);
        lay = findViewById(R.id.lay1);
        Design(1);
        imgv = findViewById(R.id.imgFlower2);
        textname = findViewById(R.id.TV_name2);
        textprice = findViewById(R.id.TV_pricef2);
        btnaddcart = findViewById(R.id.BT_add2);
        rbar = findViewById(R.id.rating2);
        lay = findViewById(R.id.lay2);
        Design(2);
        imgv = findViewById(R.id.imgFlower3);
        textname = findViewById(R.id.TV_name3);
        textprice = findViewById(R.id.TV_pricef3);
        btnaddcart = findViewById(R.id.BT_add3);
        rbar = findViewById(R.id.rating3);
        lay = findViewById(R.id.lay3);
        Design(3);
        imgv = findViewById(R.id.imgFlower4);
        textname = findViewById(R.id.TV_name4);
        textprice = findViewById(R.id.TV_pricef4);
        btnaddcart = findViewById(R.id.BT_add4);
        rbar = findViewById(R.id.rating4);
        lay = findViewById(R.id.lay4);
        Design(4);
        imgv = findViewById(R.id.imgFlower5);
        textname = findViewById(R.id.TV_name5);
        textprice = findViewById(R.id.TV_pricef5);
        btnaddcart = findViewById(R.id.BT_add5);
        rbar = findViewById(R.id.rating5);
        lay = findViewById(R.id.lay5);
        Design(5);
        imgv = findViewById(R.id.imgFlower6);
        textname = findViewById(R.id.TV_name6);
        textprice = findViewById(R.id.TV_pricef6);
        btnaddcart = findViewById(R.id.BT_add6);
        rbar = findViewById(R.id.rating6);
        lay = findViewById(R.id.lay6);
        Design(6);
        imgv = findViewById(R.id.imgFlower7);
        textname = findViewById(R.id.TV_name7);
        textprice = findViewById(R.id.TV_pricef7);
        btnaddcart = findViewById(R.id.BT_add7);
        rbar = findViewById(R.id.rating7);
        lay = findViewById(R.id.lay7);
        Design(7);
        imgv = findViewById(R.id.imgFlower8);
        textname = findViewById(R.id.TV_name8);
        textprice = findViewById(R.id.TV_pricef8);
        btnaddcart = findViewById(R.id.BT_add8);
        rbar = findViewById(R.id.rating8);
        lay = findViewById(R.id.lay8);
        Design(8);
        imgv = findViewById(R.id.imgFlower9);
        textname = findViewById(R.id.TV_name9);
        textprice = findViewById(R.id.TV_pricef9);
        btnaddcart = findViewById(R.id.BT_add9);
        rbar = findViewById(R.id.rating9);
        lay = findViewById(R.id.lay9);
        Design(9);
        imgv = findViewById(R.id.imgFlower10);
        textname = findViewById(R.id.TV_name10);
        textprice = findViewById(R.id.TV_pricef10);
        btnaddcart = findViewById(R.id.BT_add10);
        rbar = findViewById(R.id.rating10);
        lay = findViewById(R.id.lay10);
        Design(10);
    }
    void Design(int position){
        int index = page*10+position-1;
        if(flowers.size()==0){

        }
        if(index>=flowers.size()){
            lay.setVisibility(View.GONE);
            return;
        }
        lay.setVisibility(View.VISIBLE);
        imgv.setImageResource(flowers.get(index).getImgid());
        textname.setText(flowers.get(index).getFlowername());
        textprice.setText("Gi??: "+flowers.get(index).getPrice()+ " ??");
        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.BT_add1:{
                        flowerid = flowers.get(page*10+0).getFlowerid();
                        break;
                    }
                    case R.id.BT_add2:{
                        flowerid = flowers.get(page*10+1).getFlowerid();
                        break;
                    }
                    case R.id.BT_add3:{
                        flowerid = flowers.get(page*10+2).getFlowerid();
                        break;
                    }
                    case R.id.BT_add4:{
                        flowerid = flowers.get(page*10+3).getFlowerid();
                        break;
                    }
                    case R.id.BT_add5:{
                        flowerid = flowers.get(page*10+4).getFlowerid();
                        break;
                    }
                    case R.id.BT_add6:{
                        flowerid = flowers.get(page*10+5).getFlowerid();
                        break;
                    }
                    case R.id.BT_add7:{
                        flowerid = flowers.get(page*10+6).getFlowerid();
                        break;
                    }
                    case R.id.BT_add8:{
                        flowerid = flowers.get(page*10+7).getFlowerid();
                        break;
                    }
                    case R.id.BT_add9:{
                        flowerid = flowers.get(page*10+8).getFlowerid();
                        break;
                    }
                    case R.id.BT_add10:{
                        flowerid = flowers.get(page*10+9).getFlowerid();
                        break;
                    }
                }
                Intent intent = new Intent(Homepage.this, FlowerDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("flowerid", flowerid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.imgFlower1:{
                        flowerid = flowers.get(page*10+0).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower2:{
                        flowerid = flowers.get(page*10+1).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower3:{
                        flowerid = flowers.get(page*10+2).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower4:{
                        flowerid = flowers.get(page*10+3).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower5:{
                        flowerid = flowers.get(page*10+4).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower6:{
                        flowerid = flowers.get(page*10+5).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower7:{
                        flowerid = flowers.get(page*10+6).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower8:{
                        flowerid = flowers.get(page*10+7).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower9:{
                        flowerid = flowers.get(page*10+8).getFlowerid();
                        break;
                    }
                    case R.id.imgFlower10:{
                        flowerid = flowers.get(page*10+9).getFlowerid();
                        break;
                    }
                }
                Intent intent = new Intent(Homepage.this, FlowerDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("flowerid", flowerid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    class View2Apdapter extends FragmentStateAdapter {
        public View2Apdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }
        public View2Apdapter(@NonNull FragmentActivity fragmentactivity) {
            super(fragmentactivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            int imgid;// id cua drawable
            switch (position){
                case 1:{
                    imgid = R.drawable.banner2;
                    break;
                }
                case 2:{
                    imgid = R.drawable.banner3;
                    break;
                }
                default:{
                    imgid = R.drawable.banner1;
                    break;
                }
            }
            return new home_slide(imgid);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}