package com.example.projectnam;

import android.icu.util.Calendar;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CalendarDate implements CalendarView.OnDateChangeListener {
    int Year = LocalDate.now().getYear(), Month = LocalDate.now().getMonthValue(), Day = LocalDate.now().getDayOfMonth();
    int finishYear = 2033, finishMonth = 12, finishDay = 31;
    int startYear = Year, startMonth = Month, startDay = Day;
    Long tim, now = System.currentTimeMillis();
    CalendarView calendar;
    SimpleDateFormat finish = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar ca;
    int offset = 0;

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Year = year;
        Month = month + 1;
        Day = dayOfMonth;
    }

    public void setCalendar(CalendarView ca) {
        this.calendar = ca;
    }

    public void setMin() {
        calendar.setMinDate(0);
        try {
            tim = finish.parse(Integer.toString(startYear) + "-" + Integer.toString(startMonth) + "-" + Integer.toString(startDay) + " 01:12:34").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (offset == 1){
            calendar.setMinDate(now);
            calendar.setDate(tim);
        }
        else {
            calendar.setMinDate(tim);
        }
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setMax() {
        //9999년 12월 31일 값으로 최대값을 늘린 후 줄일 수 있다.
        calendar.setMaxDate(253402186354000L);
        try {
                tim = finish.parse(Integer.toString(finishYear) + "-" + Integer.toString(finishMonth) + "-" + Integer.toString(finishDay) + " 01:12:34").getTime();
            } catch (ParseException e) {
                e.printStackTrace();
        }

        if (offset == 1) calendar.setMaxDate(tim);

        else {
            if(tim==2019571954000L) calendar.setDate(now);
            else calendar.setDate(tim);
        }

    }

    public void saveFinish() {
        finishYear = Year;
        finishMonth = Month;
        finishDay = Day;
    }
    public void saveStart(){
        startYear = Year;
        startMonth = Month;
        startDay = Day;
    }

}
