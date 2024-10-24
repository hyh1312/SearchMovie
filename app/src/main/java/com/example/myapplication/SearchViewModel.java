package com.example.myapplication;

import com.example.myapplication.ui.model.ListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;


public class SearchViewModel {

    public static List<Movie> addList(Response response) throws JSONException, IOException {
        List<Movie> movieList= ListModel.movieList;
        assert response.body() != null;
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray searchArray = jsonObject.getJSONArray("Search");
        movieList.clear();
        for (int i = 0; i < searchArray.length(); i++) {
            JSONObject movieObject = searchArray.getJSONObject(i);
            String title = movieObject.getString("Title");
            String year = movieObject.getString("Year");
            String poster = movieObject.getString("Poster");
            String id = movieObject.getString("imdbID");
            movieList.add(new Movie(title, year, poster,id));
        }
        return movieList;
    }
}
