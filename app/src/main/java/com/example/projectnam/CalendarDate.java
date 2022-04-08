package com.example.projectnam;

import android.widget.CalendarView;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class CalendarDate implements CalendarView.OnDateChangeListener{
    int Year = LocalDate.now().getYear(), Month = LocalDate.MAX.getMonthValue(), Day = LocalDate.now().getDayOfMonth();

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Year = year;
        Month = month + 1;
        Day = dayOfMonth;
    }
}
