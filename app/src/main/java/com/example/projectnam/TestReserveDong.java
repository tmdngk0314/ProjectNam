package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestReserveDong extends AppCompatActivity {

    int minute, hour, year,month,day;
    TextView usetimetext, usedate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_reserve_dong);

        usetimetext = (TextView)findViewById(R.id.usetimetext);
        usedate = (TextView)findViewById(R.id.usedate);

        year = getIntent().getExtras().getInt("년");
        month = getIntent().getExtras().getInt("달");
        day = getIntent().getExtras().getInt("일");
        hour = getIntent().getExtras().getInt("시간");
        minute = getIntent().getExtras().getInt("분");

        usetimetext.setText(hour + ":" + minute + "PM");
        usedate.setText(year+"."+month+"."+day);
    }
}