package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReserveStateActivity extends AppCompatActivity {
    TextView tv_lockername, tv_startdate, tv_enddate, tv_lockernum, tv_overdue1, tv_overdue2, tv_username, tv_locationtxt;
    CallRestApi apiCaller = new CallRestApi();
    String usinglockername, startdate, enddate, location, status;
    Integer lockernum;

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
        tv_enddate.setText(enddate);
        tv_locationtxt.setText(location);




    }
}