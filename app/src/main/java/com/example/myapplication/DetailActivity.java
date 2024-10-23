package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);

        String id = getIntent().getStringExtra("id"); // id 传过来
        TextView Title = findViewById(R.id.movie_title);
        Title.setText(id);

    }
}