package com.codepath.myofficesports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AnnouncementActivity extends AppCompatActivity {

    private FloatingActionButton btnCompose;
    private FloatingActionButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        btnCompose = findViewById(R.id.btnCompose);
        btnBack = findViewById(R.id.btnBack);

        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnCompose = new Intent ()
            }
        });
    }
}