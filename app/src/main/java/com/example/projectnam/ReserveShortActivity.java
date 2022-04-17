package com.example.projectnam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.time.LocalDate;

import java.time.Month;

public class ReserveShortActivity extends AppCompatActivity {
    ImageButton imgBtnLogout,nextBtn;
    CalendarView calender;
    int Year, Month, Day ;
    long now = System.currentTimeMillis();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReserveShortActivity.this, ReserveActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_short);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        calender = (CalendarView)findViewById(R.id.calender);
        CalendarDate calendarDate = new CalendarDate();

        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveShortActivity.this, ReserveActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgBtnLogout.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        imgBtnLogout.setBackgroundResource(R.drawable.select_logout_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        imgBtnLogout.setBackgroundResource(R.drawable.select_logout);
                        return false;
                    default: return false;
                }

            }
        });
        calender.setOnDateChangeListener(calendarDate);
        calender.setMinDate(now);
        nextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveShortActivity.this, ReserveShortResultActivity.class);
                Log.e("Year",Integer.toString(Year));
                intent.putExtra("년",calendarDate.Year);
                intent.putExtra("달",calendarDate.Month);
                intent.putExtra("일",calendarDate.Day);
                startActivity(intent);
                finish();
            }
        });

        nextBtn.setOnTouchListener(new OkTouch());

    }
}