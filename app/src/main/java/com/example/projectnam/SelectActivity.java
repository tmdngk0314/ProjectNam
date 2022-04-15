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
    RelativeLayout thirdRela, firstRela, secondRela, forthRela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        imgBtnreserve =(ImageButton)findViewById(R.id.storeimg) ;
        thirdRela = (RelativeLayout)findViewById(R.id.thirdRela);
        firstRela = (RelativeLayout)findViewById(R.id.firstRela);
        secondRela = (RelativeLayout)findViewById(R.id.secondRela);
        forthRela = (RelativeLayout)findViewById(R.id.forthRela);


        startService(new Intent(this, ForcedTerminationService.class)); // 앱 강제종료 시 로그아웃하는 서비스

        firstRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, ReserveActivity.class);
                startActivity(intent);
            }
        });
        firstRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        firstRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        firstRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });
        secondRela.setOnClickListener(new View.OnClickListener() {
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
                float curX = event.getX();
                float curY = event.getY();
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
        forthRela.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        forthRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        forthRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        forthRela.setBackgroundResource(R.drawable.select_box);
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