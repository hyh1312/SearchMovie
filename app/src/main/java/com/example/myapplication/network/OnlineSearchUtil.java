package com.example.myapplication.network;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OnlineSearchUtil {
    private static final String URL = "http://www.omdbapi.com/?apikey=858fc655";

    public static void search(String query, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(query).get().build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void searchMovies(String name, Callback callback) {
        search(URL + "&s=" + name, callback);
    }

    public static void searchMoviesByPage(String name,int page, Callback callback) {
        search(URL + "&s=" + name + "&page=" + page, callback);
    }

    public static void searchDetail(String id, Callback callback) {
        search(URL + "&i=" + id + "&plot=full" , callback);
    }
}
