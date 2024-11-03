package com.example.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvYear;
    ImageView imgPoster;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvYear = itemView.findViewById(R.id.tvYear);
        imgPoster = itemView.findViewById(R.id.imgPoster);
    }
    public static MovieViewHolder create(ViewGroup parent, Context context) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }
}