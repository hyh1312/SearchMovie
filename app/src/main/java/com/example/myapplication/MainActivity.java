package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;

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



        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchMovies(et.getText().toString().trim());
                }
                return false;  // 让系统继续处理其他事件
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                searchMovies(et.getText().toString().trim());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void searchMovies(String query) {
        if (query.isEmpty()) {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);

        // pb.setVisibility(View.INVISIBLE);
        // 如何在切回 MainActivity 的时候再隐藏进度条？

    }
}

