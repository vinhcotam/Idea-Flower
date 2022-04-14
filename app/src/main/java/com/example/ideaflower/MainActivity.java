package com.example.ideaflower;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity  {
    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, Login.class);;
        FisrtTime();
        startActivity(intent);
        finish();
    }
    void FisrtTime(){
        SQLiteDatabase db = openOrCreateDatabase("FlowerStoreDB.db", MODE_PRIVATE, null);
        try{
            db.execSQL("drop table if exists Flower");
            db.execSQL("drop table if exists FirstUse");
            db.execSQL("create table if not exists FirstUse(first int unique)");
            db.execSQL("insert into FirstUse values(1)");
            //đã có = lỗi -> không insert nữa
            String sql = "Create table if not exists Flower(idflower char(50) primary key, nameflower char(50), category char(50),price int,color char(50),imgflower int,quantity int)";
            db.execSQL(sql);
            sql = " insert into Flower values" +
                    "('tr01', 'Bàng Sin mini', 'Cây', 95000, 'Xanh', "+R.drawable.bangsin+", 40)," +
                    "('p02', 'Bàng chúc ngủ ngon', 'Cây, Chậu', 180000, 'Xanh', "+ R.drawable.bangzz +", 95)," +
                    "('tr03', 'Lưỡi hổ', 'Cây', 95000, 'Xanh', "+R.drawable.luoiho+", 40)," +
                    "('p01', 'Lưỡi hổ happy birthday', 'Cây, Chậu', 95000, 'Xanh', "+R.drawable.luoihohppd+", 40)," +
                    "('fl01', 'Moon River', 'Hoa', 95000, 'Xanh', "+R.drawable.moonriver+", 40)," +
                    "('fl02', 'Pinky', 'Hoa', 95000, 'Hồng', "+R.drawable.pinky+", 40)," +
                    "('p1', 'Sen đá cá voi', 'Hoa, Chậu', 95000, 'Xanh', "+R.drawable.sebdacavoi+", 40)," +
                    "('tr05', 'Trầu bà sữa', 'Cây', 95000, 'Xanh', "+R.drawable.traubasua+", 40)," +
                    "('fl04', 'Fallforyou', 'Hoa', 95000, 'Xanh', "+R.drawable.fallforyou+", 40)," +
                    "('fl05', 'Bó hoa mẫu số 1', 'Hoa', 95000, 'Đỏ', "+R.drawable.hoa1+", 40)," +
                    "('fl06', 'Bó hoa mẫu số 2', 'Hoa', 95000, 'Trắng', "+R.drawable.hoa2+", 40)," +
                    "('fl07', 'Bó hoa mẫu số 3', 'Hoa', 95000, 'Đỏ', "+R.drawable.hoa3+", 40)," +
                    "('fl08', 'Bó hoa mẫu số 4', 'Hoa', 95000, 'Đỏ', "+R.drawable.hoa4+", 40)," +
                    "('fl09', 'Bó hoa mẫu số 5', 'Hoa', 95000, 'Đỏ', "+R.drawable.hoa5+", 40)," +
                    "('fl10', 'Bó hoa mẫu số 6', 'Hoa', 95000, 'Đỏ', "+R.drawable.hoa6+", 40)," +
                    "('fl11', 'Bó hoa mẫu số 7', 'Hoa', 95000, 'Hồng', "+R.drawable.hoa7+", 40)," +
                    "('fl12', 'Bó hoa mẫu số 8', 'Hoa', 95000, 'Trắng', "+R.drawable.hoa8+", 40)," +
                    "('fl13', 'Bó hoa mẫu số 9', 'Hoa', 95000, 'Xanh, Trắng', "+R.drawable.hoa1_date+", 40)," +
                    "('fl14', 'Bó hoa mẫu số 10', 'Hoa', 95000, 'Hồng', "+R.drawable.hoa2_date+", 40)," +
                    "('fl15', 'Bó hoa mẫu số 11', 'Hoa', 95000, 'Hồng', "+R.drawable.hoa3_date+", 40)," +
                    "('fl16', 'Bó hoa mẫu số 12', 'Hoa', 95000, 'Hồng', "+R.drawable.hoa4_date+", 40)," +
                    "('fl17', 'Hoa lan Parade', 'Hoa', 95000, 'Xanh', "+R.drawable.hoalanparade+", 40)," +
                    "('fl18', 'Bó hoa mẫu số 14', 'Hoa', 95000, 'Xanh', "+R.drawable.hoahong1+", 40)," +
                    "('fl19', 'Bó hoa mẫu số 15', 'Hoa', 95000, 'Xanh', "+R.drawable.hoa_tinhyeu+", 40)," +
                    "('p2', 'Sen đá hươu cao cổ', 'Hoa, Chậu', 95000, 'Xanh', "+R.drawable.chau1+", 40)," +
                    "('p3', 'Sen đá cún nằm ngủ', 'Hoa, Chậu', 95000, 'Xanh', "+R.drawable.chaucay+", 40)," +
                    "('p4', 'Sen đá happy birthday', 'Hoa, Chậu', 95000, 'Xanh', "+R.drawable.tree3+", 40)," +
                    "('p5', 'Sen đá voi', 'Hoa, Chậu', 95000, 'Xanh', "+R.drawable.chau2+", 40)," +
                    "('fl20', 'Lavierose', 'Hoa', 95000, 'Hồng', "+R.drawable.lavierose+", 40)";
            db.execSQL(sql);
            String sql1="create table if not exists Vote(email char(50),content char(100),numstar float,idflower char(50),foreign key (idflower)references Flower(idflower))";
            db.execSQL(sql1);
            String sql2 = "create table if not exists Flower(idflower char(50) primary key, nameflower char(50), category char(50),price int,color char(50),imgflower int,quantity int)";
            db.execSQL(sql2);
            String sql3="Create table if not exists DetailOrder(idorder int primary key, nameflower char(50), " +
                    "price int, quantity int, imgflower int,Email char(50),namecus char(50),phone int(11),location char(50))";
            db.execSQL(sql3);
        }
        catch (Exception e){
            return;
        }
    }
}