package com.example.ideaflower;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class home_slide extends Fragment {
    private int imgid;
    public home_slide(int imgid) {
        this.imgid = imgid;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_slide, container, false);
        ImageView imgview = root.findViewById(R.id.imgview);
        imgview.setImageResource(imgid);
        imgid = R.id.imgview;
        return root;
    }
}