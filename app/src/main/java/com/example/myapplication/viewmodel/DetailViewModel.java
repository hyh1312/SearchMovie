package com.example.myapplication.viewmodel;

import com.example.myapplication.ui.model.Detail;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;


public class DetailViewModel {

    public static Detail getDetail(Response response) throws JSONException, IOException {
        assert response.body() != null;
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData);
        String title = jsonObject.getString("Title");
        String year = jsonObject.getString("Year");
        String poster = jsonObject.getString("Poster");
        String id = jsonObject.getString("imdbID");
        return new Detail(title,year,poster,id);
    }
}

