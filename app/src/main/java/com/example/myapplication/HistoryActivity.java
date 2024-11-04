package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.controller.HistoryViewModel;
import java.util.Collections;

public class HistoryActivity extends MovieListBaseActivity {

    private HistoryViewModel historyViewModel;
    private Button clearButton;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setStatusBarColor(this);
        toolbar.setTitle("浏览记录");
        clearButton=findViewById(R.id.clear_button);
        clearButton.setVisibility(View.VISIBLE);
        clearButton.setOnClickListener(view -> {
            historyViewModel.deleteAll();
            movieAdapter.clear();
            Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
        });
        loadMore(true);
    }

    @SuppressLint({"NotifyDataSetChanged", "CheckResult"})
    @Override
    public void loadMore(boolean isFirstPage) {
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.getAllMovies().observe(this, movies -> {
            Collections.reverse(movies); // 按时间倒序展示
            movieAdapter.update(movies);
            isLoading(false);
        });
    }
}