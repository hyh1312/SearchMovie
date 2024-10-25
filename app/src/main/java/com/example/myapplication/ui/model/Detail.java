package com.example.myapplication.ui.model;
public class Detail {
    private final String title,year,posterUrl,id;

    public Detail(String title, String year, String posterUrl, String id) {
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
