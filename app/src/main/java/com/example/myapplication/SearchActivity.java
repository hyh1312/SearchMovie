package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.network.OnlineSearchUtil;
import com.example.myapplication.ui.model.ListModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private List<Movie> movieList = ListModel.movieList;
    private int page = 1;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        String query = getIntent().getStringExtra("query");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(movieList, this);
        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);
        Button previousButton = findViewById(R.id.btnPrevious);
        Button nextButton = findViewById(R.id.btnNext);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        Callback callback = new Callback() {
            // private boolean responseFlag=false;
            // public boolean isResponse(){ return responseFlag; }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    movieList = SearchViewModel.addList(response);
                    // responseFlag=true;
                    runOnUiThread(() -> {
                        movieAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(SearchActivity.this, "未找到电影", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    });
                }
            }
        };

        OnlineSearchUtil.searchMovies(query, callback);

        page = 1;
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    OnlineSearchUtil.searchMoviesByPage(query, page-1, callback);
                    if(true){ // callback.isResponse ?
                        page--;
                    }
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page < 100) { // maxPage?
                    OnlineSearchUtil.searchMoviesByPage(query, page+1, callback);
                    if(true){ // callback.isResponse ?
                        page++;
                    }
                }
            }
        });
        /*
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page = Integer.parseInt(editText.getText().toString().trim());
                    OnlineSearchUtil.searchMoviesByPage(query, page, callback);
                    return true;
                }
                return false;
            }
        });
*/
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieList.clear();
        movieAdapter.notifyDataSetChanged();
    }

}