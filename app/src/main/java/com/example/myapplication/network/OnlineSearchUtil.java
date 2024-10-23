package com.example.myapplication.network;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OnlineSearchUtil {
    private static final String URL = "http://www.omdbapi.com/?apikey=858fc655";

    public static void search(String query, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(query).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("--------Fail-------", "onFailure: " + e.toString());
                callback.onFailure(call, e);
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                callback.onResponse(call, response);
            }
        });
    }

    public static void searchMovies(String name, Callback callback) {
        search(URL + "&s=" + name, callback); // + "&page=" + page 实现下一页（还没写）
    }

    public static void searchDetails(String id, Callback callback) {
        search(URL + "&t=" + id, callback);
    }
}
