package com.example.ideaflower;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class load_content_home extends Fragment {
    String flowerid;
    String flowername;
    int imgid;
    int Price;
    float Rating;
    String flowerid2;
    String flowername2;
    int imgid2;
    int Price2;
    float Rating2;
    public load_content_home() {
        flowerid = "";
        imgid=0;
        Price=-1;
        Rating = 0;
        flowerid2 = "";
        imgid2=0;
        Price2=-1;
        Rating2 = 0;
    }
    public load_content_home(String flowerid, String flowername, int imgid, int Price, float Rating, String flowerid2, String flowername2, int imgid2, int Price2, float Rating2){
        this.flowerid = flowerid;
        this.flowername = flowername;
        this.imgid = imgid;
        this.Price = Price;
        this.Rating = Rating;
        this.flowerid2 = flowerid2;
        this.flowername2 = flowername2;
        this.imgid2 = imgid2;
        this.Price2 = Price2;
        this.Rating2 = Rating2;
    }
    public load_content_home(String flowerid, String flowername, int imgid, int Price, float Rating){
        this.flowerid = flowerid;
        this.flowername = flowername;
        this.imgid = imgid;
        this.Price = Price;
        this.Rating = Rating;
        this.flowerid2 = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_load_content_home, container, false);
        Design(root);
        return root;
    }
    void Design( View view){
        ImageView imv = view.findViewById(R.id.imgFlower1);
        TextView tvname = view.findViewById(R.id.TV_name1);
        TextView tvprice = view.findViewById(R.id.TV_pricef1);
        RatingBar ratingb = view.findViewById(R.id.rating1);
        imv.setImageResource(imgid);
        tvname.setText(flowername);
        tvprice.setText(Price);
        ratingb.setRating(Rating);
        if(flowerid2.equals("")){
            return;
        }
        imv = view.findViewById(R.id.imgFlower2);
        tvname = view.findViewById(R.id.TV_name2);
        tvprice = view.findViewById(R.id.TV_pricef2);
        ratingb = view.findViewById(R.id.rating2);
        try{
            imv.setImageResource(imgid);
        }
        catch(Exception e){
            imv.setImageResource(R.drawable.noimg);
        }
        tvname.setText(flowername);
        tvprice.setText(Price);
        ratingb.setRating(Rating);
    }
}