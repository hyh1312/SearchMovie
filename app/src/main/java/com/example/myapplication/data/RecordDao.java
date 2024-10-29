package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecords(Record... records);

    @Delete
    void delete(Record record);

    @Update
    public void updateRecords(Record... records);

    @Query("SELECT * FROM record")
    List<Record> getAll();

    @Query("SELECT * from record WHERE uid = :uid")
    Record getRecord(int uid);

    // ...

}