package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class ReserveTimeActivity extends AppCompatActivity {
    ImageButton nextbtn;
    TimePicker timepicker;
    int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_time);

        nextbtn = (ImageButton)findViewById(R.id.nextbtn);
        timepicker = (TimePicker)findViewById(R.id.timepicker);

        year = getIntent().getIntExtra("Year",1);
        month = getIntent().getIntExtra("Month",1);
        day = getIntent().getIntExtra("Day",1);

        hour = timepicker.getCurrentHour();
        minute = timepicker.getCurrentMinute();


        nextbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveTimeActivity.this, TestReserveDong.class);
                intent.putExtra("시간",hour);
                intent.putExtra("분",minute);
                intent.putExtra("년",year);
                intent.putExtra("달",month);
                intent.putExtra("일",day);
                startActivity(intent);
                finish();
            }
        });




    }
}