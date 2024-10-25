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

