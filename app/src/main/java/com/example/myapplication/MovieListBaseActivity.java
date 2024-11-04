package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.model.Movie;
import com.example.myapplication.controller.MovieAdapter;
import com.example.myapplication.controller.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MovieListBaseActivity extends AppCompatActivity {

    protected MovieAdapter movieAdapter;
    protected ProgressBar progressBar;
    protected RecyclerView recyclerView;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.loading_spinner);
        movieAdapter = new MovieAdapter(new ArrayList<>(), this,getApplication());
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        isLoading(true);

        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                // Toast.makeText(MovieListBaseActivity.this, "loadMore", Toast.LENGTH_SHORT).show();
                isLoading(true);
                loadMore(false);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieAdapter.clear();
    }

    public void isLoading(boolean p) {
        runOnUiThread(()-> progressBar.setVisibility(p ? View.VISIBLE : View.GONE));
    }

    public abstract void loadMore(boolean isFirstPage);

    public void setStatusBarColor(Activity activity){
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF6650a4);
    }

}

// 如何简化SearchActivity，使得不同Activity能够复用逻辑？
// 建基类