package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.network.OnlineSearchUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);

        String query = getIntent().getStringExtra("query"); // id 传过来

        OnlineSearchUtil.searchMovies(0, query, new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(DetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    assert response.body() != null;
                    String jsonData = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray searchArray = jsonObject.getJSONArray("Search");

                    // String title = movieObject.getString("Title");
                    // ...

                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(DetailActivity.this, "未找到电影", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    });
                }
            }
        });


    }
}