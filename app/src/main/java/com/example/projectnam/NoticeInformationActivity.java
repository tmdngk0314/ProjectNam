package com.example.projectnam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeInformationActivity extends AppCompatActivity {
    TextView notice, noticeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_information);

        notice = (TextView) findViewById(R.id.notice);
        noticeInfo = (TextView) findViewById(R.id.noticeInfo);



        notice.setText("OTP 사용법");
        noticeInfo.setText("저희 프작남 프로그램은 보안을 위해서 사물함을 대여할 때 OTP를 사용합니다. 또한 OTP를 편하게 입력하기 위해서 NFC를 사용할 수 있니다.");
        noticeInfo.setTextSize(Float.parseFloat("30"));
    }
}
