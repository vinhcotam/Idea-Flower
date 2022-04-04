package com.example.ideaflower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class FlowerDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);
        anhXa();
    }
    private void anhXa() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
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