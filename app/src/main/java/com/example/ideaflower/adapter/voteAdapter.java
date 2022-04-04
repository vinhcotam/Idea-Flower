package com.example.ideaflower.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideaflower.R;
import com.example.ideaflower.classs.vote;

import java.util.ArrayList;
import java.util.List;

public class voteAdapter extends RecyclerView.Adapter<voteAdapter.ViewHolder>{
    private ArrayList<vote> mListVote;
    private Context context;
    //constructor
    public voteAdapter(ArrayList<vote>mListVote,Context context){
        this.mListVote=mListVote;
        this.context=context;
    }
    @NonNull
    @Override
    public voteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull voteAdapter.ViewHolder holder, int position) {
        vote vote=mListVote.get(position);
        String name=mListVote.get(position).getEmail();
        String content=mListVote.get(position).getContent();
        holder.tv_name.setText(name);
        holder.tv_content.setText(content);
    }

    @Override
    public int getItemCount() {
        return mListVote.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_content=itemView.findViewById(R.id.tv_content);

        }
    }
}

