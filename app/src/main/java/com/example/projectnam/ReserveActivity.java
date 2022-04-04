package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;

public class ReserveActivity extends AppCompatActivity {
    ImageView imgBtnLogout, locker1,refreshBtn;
    RelativeLayout relativeLock1,notice_reserve;
    TextView lockerNum, lockerText1, lockerText2;
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
    }
}