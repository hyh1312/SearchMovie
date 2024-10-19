package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.network.OnlineSearchUtil;
import com.example.myapplication.ui.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");


        recyclerView = findViewById(R.id.recycler_view);
        List<Movie> movieList = MovieModel.movieList;
        movieAdapter = new MovieAdapter(movieList, this);

        // LinearLayoutManager 布局管理器负责摆放 Adapter 提供的每一个 ViewHolder。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        OnlineSearchUtil.searchMovies(query, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    // pb.setVisibility(View.GONE);
                    Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    assert response.body() != null;
                    String jsonData = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray searchArray = jsonObject.getJSONArray("Search");

                    movieList.clear();
                    for (int i = 0; i < searchArray.length(); i++) {
                        JSONObject movieObject = searchArray.getJSONObject(i);
                        String title = movieObject.getString("Title");
                        String year = movieObject.getString("Year");
                        String poster = movieObject.getString("Poster");

                        movieList.add(new Movie(title, year, poster));
                    }

                    runOnUiThread(() -> {
                        movieAdapter.notifyDataSetChanged();
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(SearchActivity.this, "未找到电影", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }


}