package com.example.myapplication.controller;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.model.Movie;
import com.example.myapplication.model.MovieRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

    private MovieRepository mRepository;

    private final LiveData<List<Movie>> mAllMovies;

    public HistoryViewModel(Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies() { return mAllMovies; }

    public void insert(Movie Movie) { mRepository.insert(Movie); }
    public void deleteAll(){
        mRepository.deleteAll();
    }
}