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

        Intent TestReserveDong = getIntent();

        minute = TestReserveDong.getExtras().getInt("시간");
        hour = TestReserveDong.getExtras().getInt("분");
        year = TestReserveDong.getExtras().getInt("년");
        month = TestReserveDong.getExtras().getInt("월");
        day = TestReserveDong.getExtras().getInt("일");

        usetimetext.setText(hour + ":" + minute + "PM");
        usedate.setText(year+"."+month+"."+day);
    }
}