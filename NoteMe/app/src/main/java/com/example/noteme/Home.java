package com.example.noteme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private SearchView searchView;
    private FloatingActionButton addNoteButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        addNoteButton = findViewById(R.id.addNoteButton);

        databaseHelper = new DatabaseHelper(this);

        Cursor cursor = databaseHelper.getAllEntries();

        List<Note> notes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("TITLE"));
                @SuppressLint("Range") String subtitle = cursor.getString(cursor.getColumnIndex("SUBTITLE"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("CONTENT"));
                notes.add(new Note(title, content));
            } while (cursor.moveToNext());
        }
        cursor.close();

        noteAdapter = new NoteAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        // Handle search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                noteAdapter.filter(newText); // Filter notes as the user types
                return false;
            }
        });

        // Open New Note screen on clicking the Add button
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, NewNote.class);
                startActivity(intent);
            }
        });
    }

}
