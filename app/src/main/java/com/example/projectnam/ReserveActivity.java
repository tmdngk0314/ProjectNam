package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;

public class ReserveActivity extends AppCompatActivity {
    ImageButton shortbtn, longbtn;
    String location,lockername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        shortbtn = findViewById(R.id.shortrent);
        longbtn = findViewById(R.id.longrent);
        Intent is = getIntent();
        location=is.getStringExtra("location");
        lockername=is.getStringExtra("lockername");
        CallRestApi apiCaller=new CallRestApi();
        FullReservationInfo fullReservationInfo;
        fullReservationInfo = apiCaller.loadFullReservedDates(lockername);
        if(fullReservationInfo.result.equals("success")){
            for(int i=0; i<fullReservationInfo.list.length(); i++){
                try {
                    Log.e("received..:", fullReservationInfo.list.getString(i));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }else if(fullReservationInfo.result.equals("no locker")){
            Toast.makeText(this, "존재하지 않는 사물함입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        shortbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveActivity.this, ReserveShortActivity.class);
                intent.putExtra("lockername",lockername);
                intent.putExtra("location",location);
                startActivity(intent);
                finish();
            }
        });

        shortbtn.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        shortbtn.setBackgroundResource(R.drawable.touchshortreserve);
                        return false;
                    case MotionEvent.ACTION_UP:
                        shortbtn.setBackgroundResource(R.drawable.shortrental);
                        return false;
                    default: return false;
                }

            }
        });

        longbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReserveActivity.this, ReserveLongActivity.class);
                intent1.putExtra("lockername",lockername);
                intent1.putExtra("location",location);
                startActivity(intent1);
                finish();
            }
        });

        longbtn.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        longbtn.setBackgroundResource(R.drawable.touchlongreserve);
                        return false;
                    case MotionEvent.ACTION_UP:
                        longbtn.setBackgroundResource(R.drawable.longrental);
                        return false;
                    default: return false;
                }

            }
        });



    }
}