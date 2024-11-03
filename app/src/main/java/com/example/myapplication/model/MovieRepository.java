package com.example.myapplication.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.myapplication.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    // Note that in order to unit test the MovieRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public MovieRepository(Context context) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(context);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.insert(movie);
        });
    }

    public void deleteAll() {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.deleteAll();
        });
    }
}
