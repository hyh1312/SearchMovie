package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.search_input);
        btn = findViewById(R.id.search_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovies(et.getText().toString().trim());
                return true;
            }
            return false;  // 让系统继续处理其他事件
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                searchMovies(et.getText().toString().trim());
            }
        });
    }

    private void searchMovies(String query) {
        if (query.isEmpty()) {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);

    }
}

