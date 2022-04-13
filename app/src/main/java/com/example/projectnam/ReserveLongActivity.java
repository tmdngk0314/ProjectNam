package com.example.projectnam;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ReserveLongActivity extends AppCompatActivity {
    Button restartBtn1, restartBtn2, chooseDayBtn;
    ImageButton nextBtn,imgBtnLogout;
    CalendarView calendar;
    RelativeLayout relativeCalendar;
    TextView text1;
    int year,month,day,year2,month2,day2;
    long tim, now = System.currentTimeMillis();  // 현재시간을 불러오는 변수 필요없누?

    CalendarDate calendarDate = new CalendarDate();
    int setTextLoca=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_long);

        restartBtn1 = (Button) findViewById(R.id.restartbtn1);
        restartBtn2 = (Button) findViewById(R.id.restartbtn2);
        chooseDayBtn = (Button)findViewById(R.id.chooseDayBtn);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        calendar = (CalendarView) findViewById(R.id.calendar);
        relativeCalendar = (RelativeLayout)findViewById(R.id.relativeCalendar);
        text1 = (TextView)findViewById(R.id.text1);

        calendarDate.setCalendar(calendar);

        restartBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextLoca=2;
                relativeCalendar.setVisibility(VISIBLE);
                calendar.setVisibility(VISIBLE);
                text1.setVisibility(INVISIBLE);
                calendarDate.setOffset(setTextLoca);
                calendarDate.setMax();
                calendarDate.setMin();

            }
        });
        restartBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextLoca=1;
                relativeCalendar.setVisibility(VISIBLE);
                calendar.setVisibility(VISIBLE);
                text1.setVisibility(INVISIBLE);
                calendarDate.setOffset(setTextLoca);
                calendarDate.setMax();
                calendarDate.setMin();
            }
        });

        chooseDayBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                relativeCalendar.setVisibility(INVISIBLE);
                calendar.setVisibility(GONE);
                text1.setVisibility(VISIBLE);
                if(setTextLoca==1){
                    restartBtn1.setText(calendarDate.Year+"년" + calendarDate.Month+ "월" + calendarDate.Day + "일");
                   year = calendarDate.Year;
                   month = calendarDate.Month;
                   day = calendarDate.Day;
                    calendarDate.saveStart();
                }
                else {
                    restartBtn2.setText(calendarDate.Year+"년" + calendarDate.Month+ "월" + calendarDate.Day + "일");
                    year2 = calendarDate.Year;
                    month2 = calendarDate.Month;
                    day2 = calendarDate.Day;
                    calendarDate.saveFinish();
                }
            }
        });
        calendar.setOnDateChangeListener(calendarDate);
        chooseDayBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        chooseDayBtn.setTextColor(R.drawable.choose_reserve_day_push);
                        return false;
                    case MotionEvent.ACTION_UP:
                        chooseDayBtn.setTextColor(R.drawable.choose_reserve_day);
                        return false;
                    default: return false;
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveLongActivity.this, ReserveLongResultActivity.class);
                intent.putExtra("연",year);
                intent.putExtra("월",month);
                intent.putExtra("일",day);
                intent.putExtra("연2",year2);
                intent.putExtra("월2",month2);
                intent.putExtra("일2",day2);
                startActivity(intent);
            }
        });
        imgBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveLongActivity.this, ReserveActivity.class);
                startActivity(intent);
            }
        });
    }
}