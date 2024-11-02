package com.example.myapplication.ui.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertMovies(Movie... movies);

    @Delete
    public Completable delete(Movie Movie);

    @Update
    public void updateMovies(Movie... Movies);

    @Query("SELECT * FROM movie")
    public Single<List<Movie>> getAll();

    /*
    @Query("SELECT * from Movie WHERE uid = :uid")
    public Movie getMovie(int uid);

    */

}