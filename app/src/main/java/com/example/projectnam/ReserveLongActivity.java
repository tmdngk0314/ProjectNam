package com.example.projectnam;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ReserveLongActivity extends AppCompatActivity {
    Button restartBtn1, restartBtn2, chooseDayBtn;
    CalendarView calendar;
    RelativeLayout relativeCalendar;
    long now = System.currentTimeMillis();

    int setTextLoca=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_long);

        restartBtn1 = (Button) findViewById(R.id.restartbtn1);
        restartBtn2 = (Button) findViewById(R.id.restartbtn2);
        chooseDayBtn = (Button)findViewById(R.id.chooseDayBtn);
        calendar = (CalendarView) findViewById(R.id.calendar);
        relativeCalendar = (RelativeLayout)findViewById(R.id.relativeCalendar);

        CalendarDate calendarDate = new CalendarDate();

        restartBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextLoca=1;
                relativeCalendar.setVisibility(VISIBLE);
            }
        });
        restartBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextLoca=2;
                relativeCalendar.setVisibility(VISIBLE);
            }
        });

        chooseDayBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                relativeCalendar.setVisibility(INVISIBLE);
                if(setTextLoca==1) restartBtn1.setText(calendarDate.Year+"년" + calendarDate.Month+ "월" + calendarDate.Day + "일");
                else restartBtn2.setText(calendarDate.Year+"년" + calendarDate.Month+ "월" + calendarDate.Day + "일");
            }
        });
        calendar.setOnDateChangeListener(calendarDate);
        calendar.setMinDate(now);
    }
}