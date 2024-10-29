package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.myapplication.data.Record;
import com.example.myapplication.data.RecordDao;
import com.example.myapplication.data.RecordDatabase;

import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*
        RecordDatabase db = Room.databaseBuilder(getApplicationContext(),
                RecordDatabase.class, "database-name").build();
        RecordDao recordDao = db.recordDao();
        List<Record> recordList = recordDao.getAll();
*/
    }
}