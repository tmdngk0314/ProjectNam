package com.example.projectnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeActivity extends AppCompatActivity {
    ImageButton imgBtnLogout;
    RelativeLayout relative1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        imgBtnLogout = (ImageButton) findViewById(R.id.imgBtnLogout);
        relative1 = (RelativeLayout)findViewById(R.id.relative1);



        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        relative1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(NoticeActivity.this, NoticeInformationActivity.class);
                startActivity(intent);
            }
        });

    }

}
