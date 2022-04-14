package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OtpActivity extends AppCompatActivity {
    long otp = System.currentTimeMillis();
    int curSecond;
    String cursec,cursec2;
    SharedPreferences deviceInfo;
    TextView cursecondtext,otptext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        deviceInfo=getSharedPreferences("accountOTP", 0);
        cursecondtext = (TextView)findViewById(R.id.cursecond);
        otptext = (TextView)findViewById(R.id.otptext);


        //Calendar 클래스의 getTime()함수 사용
        Calendar calendar = Calendar.getInstance();
        curSecond = calendar.get(Calendar.SECOND); // 현재 시간의 초를 불러옴

        if(curSecond>0 && curSecond<=30) {
            cursec = Integer.toString(30-curSecond);
            cursecondtext.setText(cursec);}
        else if(curSecond>30 && curSecond<=60){
            cursec2 = Integer.toString(60-curSecond);
            cursecondtext.setText(cursec2);

        }


        String otp = ManageOTP.getCurrentOTP(CurrentLoggedInID.ID, deviceInfo);
        otptext.setText(otp);




    }
}