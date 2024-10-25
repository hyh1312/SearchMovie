package com.example.myapplication;

import static com.example.myapplication.network.OnlineSearchUtil.searchDetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.model.Detail;
import com.example.myapplication.viewmodel.DetailViewModel;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView poster;
    private TextView title;
    private TextView info;
    private TextView plot;
    private TextView directorWriterCast;
    private TextView languageCountry;
    private TextView boxOfficeAwards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        poster = findViewById(R.id.movie_poster);
        title = findViewById(R.id.movie_title);
        info = findViewById(R.id.movie_info);
        plot = findViewById(R.id.movie_plot);
        directorWriterCast = findViewById(R.id.movie_director_writer_cast);
        languageCountry = findViewById(R.id.movie_language_country);
        boxOfficeAwards = findViewById(R.id.movie_boxoffice_awards);

        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);

        String id = getIntent().getStringExtra("id"); // id 传过来

        // id ->(searchutil)-> jsondata ->(viewmodel)-> ui

        searchDetail(id, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(DetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // Log.d("tag",response.body().string());
                try {
                    Detail detail = DetailViewModel.getDetail(response);
                    runOnUiThread(() -> {
                        showDetail(detail);
                        progressBar.setVisibility(View.GONE);
                    });
                } catch (IOException e) {
                    runOnUiThread(() -> {
                        Toast.makeText(DetailActivity.this, "出现IO错误", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    });
                } catch (JSONException e) {
                    runOnUiThread(() -> {
                        Toast.makeText(DetailActivity.this, "出现json错误", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        // Log.d("123",e.getMessage()+'\n'+e.getCause());
                        Toast.makeText(DetailActivity.this, "出现其他错误", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    });
                }
            }
        });

    }
    private void showDetail(Detail detail){
        title.setText(detail.getTitle());
        //
    }
}