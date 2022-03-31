package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SelectActivity extends AppCompatActivity {
    ImageButton imgBtnLogout, imgBtnreserve;
    RelativeLayout thirdRela, firstRela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        imgBtnreserve =(ImageButton)findViewById(R.id.storeimg) ;
        thirdRela = (RelativeLayout)findViewById(R.id.thirdRela);
        firstRela = (RelativeLayout)findViewById(R.id.firstRela);

        firstRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, Reserve.class);
                startActivity(intent);
            }
        });
        firstRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        firstRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        firstRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });
        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgBtnLogout.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        imgBtnLogout.setBackgroundResource(R.drawable.select_logout_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        imgBtnLogout.setBackgroundResource(R.drawable.select_logout);
                        return false;
                    default: return false;
                }

            }
        });
        thirdRela.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        thirdRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        thirdRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        thirdRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });

    }
}