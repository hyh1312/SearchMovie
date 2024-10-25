package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.network.OnlineSearchUtil;
import com.example.myapplication.ui.model.ListModel;
import com.example.myapplication.ui.model.Movie;
import com.example.myapplication.viewmodel.SearchViewModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private List<Movie> movieList = ListModel.movieList;
    private String query;
    private EditText editText;
    private Button previousButton, nextButton;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        query = getIntent().getStringExtra("query");
        movieAdapter = new MovieAdapter(movieList, this);
        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);
        previousButton = findViewById(R.id.btnPrevious);
        nextButton = findViewById(R.id.btnNext);
        editText = findViewById(R.id.etPageInput);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        tryChangePage(1);

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                tryChangePage(Integer.parseInt(editText.getText().toString().trim()));
                return true;
            }
            return false;
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryChangePage(page - 1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryChangePage(page + 1);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieList.clear();
        movieAdapter.notifyDataSetChanged();
    }

    private void tryChangePage(int newPage) {
        loading(true);
        OnlineSearchUtil.searchMoviesByPage(query, newPage, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    loading(false);
                });
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    movieList = SearchViewModel.addList(response);
                    page = newPage;
                    runOnUiThread(() -> {
                        movieAdapter.notifyDataSetChanged();
                        loading(false);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(SearchActivity.this, "未找到电影", Toast.LENGTH_SHORT).show();
                        loading(false);
                    });
                }
            }
        });
    }

    private void loading(boolean p) {
        previousButton.setEnabled(!p);
        nextButton.setEnabled(!p);
        if (p) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

}