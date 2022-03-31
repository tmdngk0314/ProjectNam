package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SelectActivity extends AppCompatActivity {
    ImageButton imgBtnLogout, imgBtnreserve;
    RelativeLayout thirdRela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        imgBtnreserve =(ImageButton)findViewById(R.id.storeimg) ;
        thirdRela = (RelativeLayout)findViewById(R.id.thirdRela);

        imgBtnreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, Reserve.class);
                startActivity(intent);
            }
        });
        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        thirdRela.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
    }
}