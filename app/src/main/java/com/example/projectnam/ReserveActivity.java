package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.TimePicker;

public class ReserveActivity extends AppCompatActivity {
    ImageView imgBtnLogout, locker1,refreshBtn;
    RelativeLayout relativeLock1,notice_reserve;
    TextView lockerNum, lockerText1, lockerText2, reserveDay;
    Button startTimeBtn,finishTimeBtn;
    int year,month,day, Hour, Minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_reserve);
        locker1 = findViewById(R.id.locker1);
        imgBtnLogout = (ImageView)findViewById(R.id.imgBtnLogout);
        refreshBtn = (ImageView)findViewById(R.id.refreshBtn);
        relativeLock1 = (RelativeLayout) findViewById(R.id.relative_lock1);
        notice_reserve = (RelativeLayout) findViewById(R.id.notice_reserve);
        lockerNum = (TextView)findViewById(R.id.lockerNum);
        lockerText1 = (TextView)findViewById(R.id.lockerText1);
        lockerText2 = (TextView)findViewById(R.id.lockerText2);
        reserveDay = (TextView)findViewById(R.id.reserveDay);
        startTimeBtn = (Button)findViewById(R.id.startTimeBtn);
        finishTimeBtn = (Button)findViewById(R.id.finishTimeBtn);



        Intent reserveActivity = getIntent();

        year = reserveActivity.getExtras().getInt("년");
        month = reserveActivity.getExtras().getInt("월");
        day = reserveActivity.getExtras().getInt("일");

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scaleupandtranslate);


        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveActivity.this, ReserveDayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        locker1.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        locker1.setBackgroundResource(R.drawable.lock2_push);
                        return false;
                    case MotionEvent.ACTION_UP:
                        locker1.setBackgroundResource(R.drawable.lock1);
                        return false;
                    default: return false;
                }

            }
        });

        locker1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                {
                    reserveDay.setText(year+"년 "+month+"월 "+day+"일");


                    //옮길 예정
                    if(!lockerNum.getText().equals("1번 사물함")) {
                        lockerNum.setText(R.string.locker1);
                        notice_reserve.setVisibility(View.VISIBLE);
                        notice_reserve.startAnimation(anim);
                    }
                }
            }
        });

        refreshBtn.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        refreshBtn.setBackgroundResource(R.drawable.reserve_refresh_push);
                        return false;
                    case MotionEvent.ACTION_UP:
                        refreshBtn.setBackgroundResource(R.drawable.reservation_refresh);
                        return false;
                    default: return false;
                }

            }
        });

        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = new TimePickerDialog(ReserveActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Minute = minute;
                    }
                },Hour,Minute,false);

                timePicker.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                timePicker.show();
            }
        });
    }
}