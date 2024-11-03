package com.example.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {
    @PrimaryKey
    public int uid;
    public int time;
    private final String title, year, posterUrl, id;

    public Movie(String title, String year, String posterUrl, String id) {
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getId() {
        return id;
    }
}
