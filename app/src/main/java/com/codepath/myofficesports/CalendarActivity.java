package com.codepath.myofficesports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;

import java.util.Calendar;

import static com.codepath.myofficesports.R.id.calendarView;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendarView);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener((View.OnClickListener) this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date =  month + "/"+ dayOfMonth + "/"+ year;

                Intent intent = new Intent(CalendarActivity.this, CalendarView.class);
                intent.putExtra("date", date);

                startActivity(intent);
            }
        });


    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(CalendarActivity.this, MainActivity.class));
        }
    }
}