package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.network.OnlineSearchUtil;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends MovieListBaseActivity {

    private String query;
    protected int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        query = getIntent().getStringExtra("query");
        toolbar.setTitle("搜索结果");
        loadMore();
    }

    @Override
    public void loadMore() {
        isLoading(true);
        OnlineSearchUtil.searchMoviesByPage(query, page, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    isLoading(false);
                });
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    movieAdapter.addAll( OnlineSearchUtil.getList(response) );
                    page++;
                    runOnUiThread(() -> {
                        movieAdapter.notifyDataSetChanged();
                        isLoading(false);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(SearchActivity.this, "未找到电影", Toast.LENGTH_SHORT).show();
                        isLoading(false);
                    });
                }
            }
        });
    }
}