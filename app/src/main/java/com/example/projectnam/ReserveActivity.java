package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ReserveActivity extends AppCompatActivity {
    ImageView imgBtnLogout, locker1,refreshBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_reserve);
        locker1 = findViewById(R.id.locker1);
        imgBtnLogout = (ImageView)findViewById(R.id.imgBtnLogout);
        refreshBtn = (ImageView)findViewById(R.id.refreshBtn);


        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveActivity.this, ReserveDayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        locker1.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        locker1.setBackgroundResource(R.drawable.lock2_push);
                        return false;
                    case MotionEvent.ACTION_UP:
                        locker1.setBackgroundResource(R.drawable.lock1);
                        return false;
                    default: return false;
                }

            }
        });

        refreshBtn.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        refreshBtn.setBackgroundResource(R.drawable.reserve_refresh_push);
                        return false;
                    case MotionEvent.ACTION_UP:
                        refreshBtn.setBackgroundResource(R.drawable.reservation_refresh);
                        return false;
                    default: return false;
                }

            }
        });
    }
}