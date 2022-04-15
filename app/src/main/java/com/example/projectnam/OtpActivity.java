package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    long otp = System.currentTimeMillis();
    int curSecond;
    String remainsec;
    SharedPreferences deviceInfo;
    TextView cursecondtext,otptext;
    ProgressBar otpprogress;
    boolean initiate=true;
    public ScheduledExecutorService exeService;

    @Override
    protected void onStart() {
        super.onStart();
        if(ManageOTP.getCurrentOTP(CurrentLoggedInID.ID, deviceInfo).equals("no key found")){
            Toast.makeText(this, "OTP Key가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        deviceInfo=getSharedPreferences("accountOTP", 0);
        cursecondtext = (TextView)findViewById(R.id.cursecond);
        otptext = (TextView)findViewById(R.id.otptext);
        otpprogress = (ProgressBar)findViewById(R.id.otpprogress);

        otpprogress.setProgress(15);

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
                                remainsec =Integer.toString(30-(curSecond%30));
                                if(initiate==false || remainsec.equals("30")) {
                                    otptext.setText(otp);
                                    initiate=true;
                                }
                                cursecondtext.setText(remainsec);
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