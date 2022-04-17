package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {
    public static Activity menu_activity;
    ImageButton imgBtnLogout, imgBtnreserve;
    RelativeLayout myInfoRela, reserveRela, OTPRela, noticeRela;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        imgBtnreserve =(ImageButton)findViewById(R.id.storeimg) ;
        myInfoRela = (RelativeLayout)findViewById(R.id.thirdRela);
        reserveRela = (RelativeLayout)findViewById(R.id.firstRela);
        OTPRela = (RelativeLayout)findViewById(R.id.secondRela);
        noticeRela = (RelativeLayout)findViewById(R.id.forthRela);


        startService(new Intent(this, ForcedTerminationService.class)); // 앱 강제종료 시 로그아웃하는 서비스



        reserveRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, ReserveActivity.class);
                startActivity(intent);
            }
        });
        reserveRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        reserveRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        reserveRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });
        OTPRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, OtpActivity.class);
                startActivity(intent);

            }
        });
        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                CallRestApi apiCaller = new CallRestApi();
                String result = apiCaller.logout();
                if(result.compareTo("success")==0) {
                    CurrentLoggedInID.isLoggedIn=false;
                    Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
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
        noticeRela.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        noticeRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        noticeRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        noticeRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });
        myInfoRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,EmailVerificationActivity.class);
                startActivity(intent);
            }
        });
        myInfoRela.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        myInfoRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        myInfoRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }
            }
        });
        OTPRela.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        OTPRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        OTPRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }
            }
        });


    }
    @Override
    protected void onDestroy() {

        Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        CallRestApi apiCaller = new CallRestApi();
        apiCaller.logout();
        CurrentLoggedInID.isLoggedIn=false;
        CurrentLoggedInID.ID="";
        super.onDestroy();
    }
}