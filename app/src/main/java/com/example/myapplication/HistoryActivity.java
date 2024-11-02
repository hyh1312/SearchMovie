package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.room.Room;

import com.example.myapplication.model.MovieDao;
import com.example.myapplication.model.MovieRoomDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryActivity extends MovieListBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        toolbar.setTitle("浏览历史");
        loadMore();
    }

    @SuppressLint({"NotifyDataSetChanged", "CheckResult"})
    @Override
    public void loadMore() {

        isLoading(false);
    }

/*
    @SuppressLint({"NotifyDataSetChanged", "CheckResult"})
    @Override
    public void loadMore() {
        MovieRoomDatabase db = Room.databaseBuilder(getApplicationContext(),
                MovieRoomDatabase.class, "database-name").build();
        MovieDao movieDao = db.movieDao();
        movieDao.getAll()
                .subscribeOn(Schedulers.io())  // 在 I/O 线程中执行查询
                .observeOn(AndroidSchedulers.mainThread())  // 在主线程中处理结果
                .subscribe(movies -> {
                    movieList = movies;
                    movieAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "对的对的", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(this, "啥都木有", Toast.LENGTH_SHORT).show();
                });
        isLoading(false);
    }

 */

}