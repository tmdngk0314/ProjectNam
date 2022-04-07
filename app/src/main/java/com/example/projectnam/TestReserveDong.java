package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        year = getIntent().getIntExtra("year",1);
        month = getIntent().getIntExtra("month",1);
        day = getIntent().getIntExtra("day",1);
        hour = getIntent().getIntExtra("hour",1);
        minute = getIntent().getIntExtra("minute",1);

        usetimetext.setText(hour + ":" + minute + "PM");
        usedate.setText(year+"."+month+"."+day);
    }
}