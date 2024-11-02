package com.example.myapplication.network;

import com.example.myapplication.ui.model.Detail;
import com.example.myapplication.ui.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

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
        call.enqueue(callback);
    }
    public static void searchMoviesByPage(String name,int page, Callback callback) {
        search(URL + "&s=" + name + "&page=" + page, callback);
    }
    public static void searchDetail(String id, Callback callback) {
        search(URL + "&i=" + id + "&plot=full" , callback);
    }
    public static void addToList(List<Movie> movieList, Response response) throws JSONException, IOException {
        assert response.body() != null;
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray searchArray = jsonObject.getJSONArray("Search");
        for (int i = 0; i < searchArray.length(); i++) {
            JSONObject movieObject = searchArray.getJSONObject(i);
            String title = movieObject.getString("Title");
            String year = movieObject.getString("Year");
            String poster = movieObject.getString("Poster");
            String id = movieObject.getString("imdbID");
            movieList.add(new Movie(title, year, poster,id));
        }
    }
    public static Detail getDetail(Response response) throws JSONException, IOException {
        assert response.body() != null;
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData);
        return new Detail(
                jsonObject.optString("Title"),
                jsonObject.optString("Year"),
                jsonObject.optString("Rated"),
                jsonObject.optString("Released"),
                jsonObject.optString("Runtime"),
                jsonObject.optString("Genre"),
                jsonObject.optString("Director"),
                jsonObject.optString("Writer"),
                jsonObject.optString("Actors"),
                jsonObject.optString("Plot"),
                jsonObject.optString("Language"),
                jsonObject.optString("Country"),
                jsonObject.optString("Awards"),
                jsonObject.optString("Poster"),
                jsonObject.optString("Metascore"),
                jsonObject.optString("imdbRating"),
                jsonObject.optString("imdbVotes"),
                jsonObject.optString("imdbID"),
                jsonObject.optString("Type"),
                jsonObject.optString("DVD"),
                jsonObject.optString("BoxOffice"),
                jsonObject.optString("Production"),
                jsonObject.optString("Website")
        );
    }
}
