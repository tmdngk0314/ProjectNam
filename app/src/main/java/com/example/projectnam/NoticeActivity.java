package com.example.projectnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeActivity extends AppCompatActivity {

    RelativeLayout notice_relative1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        notice_relative1 = (RelativeLayout) findViewById(R.id.notice_relative1);

        notice_relative1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(NoticeActivity.this, NoticeInformationActivity.class);
                startActivity(intent);
            }
        });
    }

}
