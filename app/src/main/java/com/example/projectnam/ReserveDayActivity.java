package com.example.projectnam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.time.LocalDate;

import java.time.Month;

public class ReserveDayActivity extends AppCompatActivity {
    ImageButton imgBtnLogout,nextBtn;
    CalendarView calender;
    int Year = LocalDate.now().getYear(), Month = LocalDate.now().getMonthValue()-1, Day = LocalDate.now().getDayOfMonth();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_day);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        calender = (CalendarView)findViewById(R.id.calender);

        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveDayActivity.this, SelectActivity.class);
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
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveDayActivity.this, ReserveActivity.class);
                intent.putExtra("년",Year);
                intent.putExtra("월",Month+1);
                intent.putExtra("일",Day);
                startActivity(intent);
                finish();
            }
        });

        nextBtn.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        nextBtn.setBackgroundResource(R.drawable.ok_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        nextBtn.setBackgroundResource(R.drawable.ok);
                        return false;
                    default: return false;
                }

            }
        });


    }
}