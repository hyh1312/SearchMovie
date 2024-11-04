package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private boolean isSearching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xFF6650a4);

        et = findViewById(R.id.search_input);
        Button sbtn = findViewById(R.id.search_button);
        ImageButton hbtn = findViewById(R.id.history_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hbtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, HistoryActivity.class)));
        sbtn.setOnClickListener(view -> searchMovies(et.getText().toString().trim()));

        et.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovies(et.getText().toString().trim());
                return true;
            }
            return true;
        });
    }

    private void searchMovies(@NonNull String query) {
        if (isSearching) return;
        if (query.isEmpty()) {
            Toast.makeText(this, "请输入电影名称", Toast.LENGTH_SHORT).show();
            return;
        }
        isSearching = true;
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isSearching = false;
    }
}

