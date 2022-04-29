package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReserveLongResultActivity extends AppCompatActivity {
    int year,month,day,year2,month2,day2;
    TextView usedate1, usedate2,tv_location, tv_lockername ;
    ImageButton ok;
    String location,lockername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_long_result);

        usedate1 = (TextView)findViewById(R.id.usedate);
        usedate2 = (TextView)findViewById(R.id.usedate2);
        ok = (ImageButton)findViewById(R.id.nextBtn);
        tv_location=(TextView)findViewById(R.id.tv_location);
        tv_lockername=(TextView)findViewById(R.id.tv_lockername);

        year = getIntent().getExtras().getInt("연"); //키 값 수정하기
        month = getIntent().getExtras().getInt("월"); // 키 값 수정하기
        day = getIntent().getExtras().getInt("일"); //  키 값 수정하기
        year2 = getIntent().getExtras().getInt("연2"); //키 값 수정하기
        month2 = getIntent().getExtras().getInt("월2"); // 키 값 수정하기
        day2 = getIntent().getExtras().getInt("일2");
        location=getIntent().getExtras().getString("location");
        lockername=getIntent().getExtras().getString("lockername");

        usedate1.setText(year+"."+month+"."+day);
        usedate2.setText("~"+year2+"."+month2+"."+day2);
        tv_location.setText(location);
        tv_lockername.setText(lockername);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}