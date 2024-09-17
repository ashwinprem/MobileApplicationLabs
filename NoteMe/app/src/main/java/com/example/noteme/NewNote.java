package com.example.noteme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NewNote extends AppCompatActivity {

    private Button doneButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        doneButton = findViewById(R.id.doneButton);
        backButton = findViewById(R.id.backButton);

        // Go back to Home screen on clicking Done button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewNote.this, Home.class);
                startActivity(intent);
                finish(); // Optionally close NewNote activity
            }
        });

        // Go back to Home screen on clicking Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewNote.this, Home.class);
                startActivity(intent);
                finish(); // Optionally close NewNote activity
            }
        });
    }
}
