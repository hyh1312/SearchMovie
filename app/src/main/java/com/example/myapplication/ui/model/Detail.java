package com.example.myapplication.ui.model;
public class Detail {
    private final String title, year, rated, released, runtime, genre, director, writer, actors, plot, language, country, awards, posterUrl, metascore, imdbRating, imdbVotes, imdbID, type, dvdRelease, boxOffice, production, website;

    public Detail(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String posterUrl, String metascore, String imdbRating, String imdbVotes, String imdbID, String type, String dvdRelease, String boxOffice, String production, String website) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.posterUrl = posterUrl;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        this.type = type;
        this.dvdRelease = dvdRelease;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
    }

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getRated() { return rated; }
    public String getReleased() { return released; }
    public String getRuntime() { return runtime; }
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public String getWriter() { return writer; }
    public String getActors() { return actors; }
    public String getPlot() { return plot; }
    public String getLanguage() { return language; }
    public String getCountry() { return country; }
    public String getAwards() { return awards; }
    public String getPosterUrl() { return posterUrl; }
    public String getMetascore() { return metascore; }
    public String getImdbRating() { return imdbRating; }
    public String getImdbVotes() { return imdbVotes; }
    public String getImdbID() { return imdbID; }
    public String getType() { return type; }
    public String getDvdRelease() { return dvdRelease; }
    public String getBoxOffice() { return boxOffice; }
    public String getProduction() { return production; }
    public String getWebsite() { return website; }
}
