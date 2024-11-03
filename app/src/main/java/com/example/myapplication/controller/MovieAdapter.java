package com.example.myapplication.controller;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.DetailActivity;
import com.example.myapplication.model.Movie;
import com.example.myapplication.model.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;
    private MovieRepository movieRepository;

    public MovieAdapter(List<Movie> movieList, Context context,Application application) {
        this.movieList = movieList;
        this.context = context;
        movieRepository = new MovieRepository(application);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MovieViewHolder.create(parent,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getYear());
        Glide.with(context).load(movie.getPosterUrl()).into(holder.imgPoster);

        holder.itemView.setOnClickListener(v -> {
            movieRepository.insert(movie);

/*
            movieRepository.getAllMovies().observe((LifecycleOwner) context, movies -> {
                Log.d("insert","title "+ movies.get(0).getTitle());
            });
 */

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", movie.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(List<Movie> movies) {
        movieList = new ArrayList<>(movies);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add(Movie movie) {
        movieList.add(movie);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addAll(List<Movie> movies) {
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clear() {
        movieList.clear();
        notifyDataSetChanged();
    }

}
