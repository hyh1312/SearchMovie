package com.example.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Movie Movie); // Completable

    @Query("SELECT * FROM movie_table")
    public LiveData<List<Movie>> getAll(); // Single

    @Delete
    public void delete(Movie Movie);

    @Query("DELETE FROM movie_table")
    void deleteAll();
/*

    @Update
    public void updateMovies(Movie... Movies);

    @Query("SELECT * from movie_table WHERE uid = :uid")
    public Movie getMovie(int uid);

*/

}