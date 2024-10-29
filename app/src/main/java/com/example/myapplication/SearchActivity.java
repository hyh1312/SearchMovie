package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private int page = 1;


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        query = getIntent().getStringExtra("query");
        movieAdapter = new MovieAdapter(movieList, this);
        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadMore();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            LinearLayoutManager layoutManager;
            int itemCount, lastPosition, lastItemCount=-1;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                // super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    itemCount = layoutManager.getItemCount();
                    lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                } else {
                    Log.e("OnLoadMoreListener", "The OnLoadMoreListener only support LinearLayoutManager");
                    return;
                }

                if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
                    //lastItemCount 是为了防止加载数据后，位置仍然符合lastPosition == itemCount - 1，因此会继续加载
                    lastItemCount = itemCount;
                    loadMore();
                }
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

    private void loadMore() {
        loading(true);
        OnlineSearchUtil.searchMoviesByPage(query, page, new Callback() {
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
                    SearchViewModel.addtoList(movieList,response);
                    page++;
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
        if (p) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

}