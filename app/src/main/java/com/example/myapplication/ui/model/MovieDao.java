package com.example.myapplication.ui.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(Movie... movies);

    @Delete
    void delete(Movie Movie);

    @Update
    public void updateMovies(Movie... Movies);

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * from Movie WHERE uid = :uid")
    Movie getMovie(int uid);

    // ...

}