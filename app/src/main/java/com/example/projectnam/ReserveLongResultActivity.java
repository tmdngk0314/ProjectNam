package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReserveLongResultActivity extends AppCompatActivity {
    int year,month,day;
    TextView usedate1, usedate2 ;
    ImageButton ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_long_result);

        usedate1 = (TextView)findViewById(R.id.usedate);
        usedate2 = (TextView)findViewById(R.id.usedate2);
        ok = (ImageButton)findViewById(R.id.nextBtn);

        year = getIntent().getExtras().getInt("년"); //키 값 수정하기
        month = getIntent().getExtras().getInt("달"); // 키 값 수정하기
        day = getIntent().getExtras().getInt("일"); //  키 값 수정하기

        usedate1.setText(year+"."+month+"."+day);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReserveLongResultActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}