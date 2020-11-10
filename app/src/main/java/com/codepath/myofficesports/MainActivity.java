package com.codepath.myofficesports;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import java.util.Calendar;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    ImageView imgCalendar = (ImageView) findViewById(R.id.imgCalendar);
    imgCalendar.setOnClickListener((View.OnClickListener) this);

    ImageView imgAnnouncements = findViewById(R.id.imgAnnouncement);
    imgAnnouncements.setOnClickListener((View.OnClickListener) this);

    ImageView imgRoster = findViewById(R.id.imgRoster);
    imgRoster.setOnClickListener((View.OnClickListener) this);

    ImageView imgMessenger = findViewById(R.id.imgMessenger);
    imgMessenger.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgCalendar:
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
                break;

            case R.id.imgAnnouncement:
                startActivity(new Intent(MainActivity.this, AnnouncementActivity.class));
                break;


        }

    }
}

