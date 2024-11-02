package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.room.Room;
import com.example.myapplication.ui.model.Movie;
import com.example.myapplication.ui.model.MovieDao;
import com.example.myapplication.ui.model.MovieDatabase;

public class HistoryActivity extends MovieListBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        toolbar.setTitle("浏览历史");
        // loadMore();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void loadMore() {
        MovieDatabase db = Room.databaseBuilder(getApplicationContext(),
                MovieDatabase.class, "database-name").build();
        MovieDao movieDao = db.recordDao();
        movieList = movieDao.getAll();
        movieAdapter.notifyDataSetChanged();
        isLoading(false);
    }
}