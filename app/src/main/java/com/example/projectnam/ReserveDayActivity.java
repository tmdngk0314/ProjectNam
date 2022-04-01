package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class ReserveDayActivity extends AppCompatActivity {
    ImageButton imgBtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_day);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);

        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ReserveDayActivity.this, SelectActivity.class);
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

    }
}