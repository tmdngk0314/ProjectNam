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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_time);

        nextbtn = (ImageButton)findViewById(R.id.nextbtn);
        timepicker = (TimePicker)findViewById(R.id.timepicker);

        int Hour = timepicker.getHour();
        int Minute = timepicker.getMinute();


        nextbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveTimeActivity.this, TestReserveDong.class);
                intent.putExtra("시간",Hour);
                intent.putExtra("분",Minute);
                startActivity(intent);
                finish();
            }
        });




    }
}