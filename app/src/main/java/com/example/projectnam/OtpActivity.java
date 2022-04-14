package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    long otp = System.currentTimeMillis();
    int curSecond;
    String cursec,cursec2;
    SharedPreferences deviceInfo;
    TextView cursecondtext,otptext;
    public ScheduledExecutorService exeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        deviceInfo=getSharedPreferences("accountOTP", 0);
        cursecondtext = (TextView)findViewById(R.id.cursecond);
        otptext = (TextView)findViewById(R.id.otptext);


        Runnable runn = new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                curSecond = calendar.get(Calendar.SECOND);
                String otp = ManageOTP.getCurrentOTP(CurrentLoggedInID.ID, deviceInfo);
                //1초마다 동작시킬 코드
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                otptext.setText(otp);
                                if(curSecond>0 && curSecond<=30) {
                                    cursec = Integer.toString(30-curSecond);
                                    cursecondtext.setText(cursec);}
                                else if(curSecond>30 && curSecond<=60){
                                    cursec2 = Integer.toString(60-curSecond);
                                    cursecondtext.setText(cursec2);
                                }  //1초마다 동작시킬 ui코드
                            }
                        });
                    }
                }).start();
            }
        };
        // 1초마다 실행하기 시작
        exeService= Executors.newSingleThreadScheduledExecutor();
        exeService.scheduleAtFixedRate(runn, 0,1, TimeUnit.SECONDS);


        //onDestroy 오버라이드해서 이거 꼭 첫줄에 넣어야함


    }

    @Override
    protected void onDestroy() {
        exeService.shutdownNow();
        super.onDestroy();
    }
}