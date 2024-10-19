package com.example.myapplication.ui.model;

import com.example.myapplication.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieModel {
    public static List<Movie> movieList = new ArrayList<Movie>();

    public void addMovie(Movie movie){
        movieList.add(movie);
    }

    public void clearMovie(){
        movieList.clear();
    }
}
