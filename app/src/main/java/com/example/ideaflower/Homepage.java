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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ideaflower.adapter.ContentAdapter;
import com.example.ideaflower.classs.Flower;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        email = bundle.getString("email");
//        Drawable drawable = (Drawable) getDrawable(R.drawable.banner1);
//        ImageView img = (ImageView) findViewById(R.id.imageView);
//        img.setImageDrawable(getDrawable(R.drawable.banner1));
        ViewPager2 vp2 = findViewById(R.id.vp2_title);
        //setContentView(R.layout.fragment_slide);
        vp2.setAdapter(
                new View2Apdapter(this)
        );
        //setContentView(R.layout.activity_homepage);
        db = openOrCreateDatabase("FlowerShop.db", MODE_PRIVATE, null);
        addFlower();
        LoadContent();
    }
    ArrayList<Flower> flowers = new ArrayList<Flower>();
    String email="";
    SQLiteDatabase db = null;
    void LoadContent() {
        RecyclerView recyclerView = findViewById(R.id.ViewContent);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ContentAdapter contentAdapter = new ContentAdapter(flowers, getApplicationContext());
        recyclerView.setAdapter(contentAdapter);
    }
    void addFlower(){
        String sql = "Create table if not exists Flower(idflower char(50) primary key, nameflower char(50), category char(50),price int,color char(50),imgflower int,quantity int)";
        db.execSQL(sql);
        sql = "Insert into Flower values ('lver', 'Lavie Rose', 'Hoa', 80000, 'Trắng',"+R.drawable.lavierose+", 20),"+
                "('jdf', 'RedRose', 'Hoa', 78000, 'Đỏ',"+R.drawable.hoahong1+", 20)";
        db.execSQL(sql);
        sql = "select * from Flower";
        Cursor cursor = db.rawQuery(sql, null);
        try{
            while (!cursor.isLast()){
                cursor.moveToNext();
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String category = cursor.getString(2);
                int price = cursor.getInt(3);
                String color =cursor.getString(4);
                int imgid = cursor.getInt(5);
                int quantity = cursor.getInt(6);
                Flower fl = new Flower(id, name, category, price, color, imgid, quantity);
                flowers.add(fl);
            }
        }
        catch(Exception e){
            return;
        }
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
            //img.setImageResource(R.drawable.banner1);

            return new home_slide(imgid);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}