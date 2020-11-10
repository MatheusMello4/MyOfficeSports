package com.codepath.myofficesports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalendarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        TextView txtDate = findViewById(R.id.txtDate);
        Button btnCalendar = findViewById(R.id.btnEvent);

        String date = getIntent().getStringExtra("date");
        if(date != null){
            txtDate.setText(date);

        }

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarView.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}