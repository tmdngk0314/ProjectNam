package com.example.projectnam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
    String location,lockername;
    int Year, Month, Day ;
    long now = System.currentTimeMillis();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReserveShortActivity.this, ReserveActivity.class);
        intent.putExtra("location", location);
        intent.putExtra("lockername", lockername);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_short);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        calender = (CalendarView)findViewById(R.id.calender);
        Intent is = getIntent();
        location=is.getStringExtra("location");
        lockername=is.getStringExtra("lockername");
        CalendarDate calendarDate = new CalendarDate();
        Log.e("locker name -",":"+lockername);
        Log.e("location -",":"+location);

        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveShortActivity.this, ReserveActivity.class);
                intent.putExtra("location", location);
                intent.putExtra("lockername", lockername);
                startActivity(intent);
                finish();
            }
        });

        calender.setOnDateChangeListener(calendarDate);
        calender.setMinDate(now+86400000);
        calender.setDate(now+86400000);
        nextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String startdate=calendarDate.Year+"-"+calendarDate.Month+"-"+calendarDate.Day;
                String enddate=startdate;
                CallRestApi apiCaller=new CallRestApi();
                String result=apiCaller.reserve(lockername, startdate, enddate);
                if(result.equals("success")) {
                    Intent intent = new Intent(ReserveShortActivity.this, ReserveShortResultActivity.class);
                    intent.putExtra("???", calendarDate.Year);
                    intent.putExtra("???", calendarDate.Month);
                    intent.putExtra("???", calendarDate.Day);
                    intent.putExtra("lockername", lockername);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }else if(result.equals("diffIP")){
                    Log.e("Login Session", "?????? ???????????? ??????????????????" );
                    Toast.makeText(ReserveShortActivity.this, "?????? ???????????? ??????????????? ???????????????.", Toast.LENGTH_SHORT).show();
                    apiCaller.logout();
                    ActivityCompat.finishAffinity(ReserveShortActivity.this);
                    System.exit(0);
                }else if(result.equals("not idle")){
                    Log.e("not idle:", "not idle");
                    Toast.makeText(ReserveShortActivity.this, "????????? ?????? ?????? ????????? ??? ????????????.", Toast.LENGTH_SHORT).show();
                }else if(result.equals("overdue")){
                    Toast.makeText(ReserveShortActivity.this, "?????? ?????? ????????? ??? ????????????.", Toast.LENGTH_SHORT).show();
                }else if(result.equals("full")){
                    Toast.makeText(ReserveShortActivity.this, "?????? ????????? ????????? ?????? ????????????.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ReserveShortActivity.this, "unknown statement", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        nextBtn.setBackgroundResource(R.drawable.touchreserve);
                        return false;
                    case MotionEvent.ACTION_UP:
                        nextBtn.setBackgroundResource(R.drawable.reservebtn);
                        return false;
                    default: return false;
                }
            }
        });

    }
}