package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TestReserveDong extends AppCompatActivity {

    int year,month,day;
    TextView  usedate ;
    ImageButton ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_reserve_dong);

        usedate = (TextView)findViewById(R.id.usedate);
        ok = (ImageButton)findViewById(R.id.nextBtn);

        year = getIntent().getExtras().getInt("년");
        month = getIntent().getExtras().getInt("달");
        day = getIntent().getExtras().getInt("일");


        usedate.setText(year+"."+month+"."+day);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestReserveDong.this, SelectActivity.class);
                startActivity(intent);
            }
        });


    }
}