package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ReserveStateActivity extends AppCompatActivity {
    TextView tv_lockername, tv_startdate, tv_enddate, tv_lockernum, tv_overdue1, tv_overdue2, tv_username, tv_locationtxt;
    String usinglockername, startdate, enddate, location, status;
    Integer lockernum;
    ImageButton nextBtn;
    ImageButton btn_cancelreserved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_state);

        tv_lockername = (TextView) findViewById(R.id.lockername);
        tv_locationtxt = (TextView)findViewById(R.id.locationtxt);
        tv_startdate = (TextView) findViewById(R.id.startdate);
        tv_enddate = (TextView) findViewById(R.id.enddate);
        tv_lockernum = (TextView) findViewById(R.id.lockernum);
        tv_overdue1 = (TextView) findViewById(R.id.overdue1);
        tv_overdue2 = (TextView) findViewById(R.id.overdue2);
        tv_username = (TextView) findViewById(R.id.username);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        btn_cancelreserved = (ImageButton)findViewById(R.id.btn_cancelreserve);
        Intent intent = getIntent();
        status=intent.getStringExtra("result");
        usinglockername = intent.getStringExtra("usinglockername");
        startdate = intent.getStringExtra("startdate");
        enddate = intent.getStringExtra("enddate");
        location = intent.getStringExtra("location");
        if(status.equals("using")) {
            lockernum = intent.getIntExtra("lockernum", 0);
            tv_lockernum.setText(Integer.toString(lockernum));
        }
        tv_username.setText(CurrentLoggedInID.name);
        tv_lockername.setText(usinglockername);
        tv_startdate.setText(startdate);
        tv_enddate.setText(" ~ "+enddate);
        tv_locationtxt.setText(location);

        if(status.equals("overdue")){
            tv_overdue1.setText("O");
            tv_overdue2.setVisibility(View.VISIBLE);
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveStateActivity.this, SelectActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        nextBtn.setBackgroundResource(R.drawable.ok_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        nextBtn.setBackgroundResource(R.drawable.ok);
                        return false;
                    default: return false;
                }
            }
        });

        btn_cancelreserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences deviceSettings = getSharedPreferences("deviceSettings", 0);
                CallRestApi apiCaller=new CallRestApi();
                String result = apiCaller.CancelReservation(deviceSettings);
                if(result.equals("diffIP")){
                    Log.e("Login Session", "다른 기기에서 로그인되었음" );
                    Toast.makeText(ReserveStateActivity.this, "다른 기기에서 로그인되어 종료합니다.", Toast.LENGTH_SHORT).show();
                    ActivityCompat.finishAffinity(ReserveStateActivity.this);
                    System.exit(0);
                }
                else if(result.equals("not reserved")){
                    Toast.makeText(ReserveStateActivity.this,  "예약된 사물함이 없습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(result.equals("success")){
                    Toast.makeText(ReserveStateActivity.this,  "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });




    }
}