package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {
    public static Activity menu_activity;
    public static Boolean isLoggedOutByButton=false;
    ImageButton imgBtnLogout, imgBtnreserve, imgBtnotpcheck, imgBtnmyinfo, imgBtnnotice;
    RelativeLayout myInfoRela, reserveRela, OTPRela, noticeRela;
    NfcAdapter mNfcAdapter;


    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onTouchReserve(MotionEvent event, RelativeLayout a){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                a.setBackgroundResource(R.drawable.select_box_touch);
                return false;
            case MotionEvent.ACTION_UP:
                a.setBackgroundResource(R.drawable.select_box);
                return false;
            default: return false;
        }
    }

    public void onClickReserve(View v){
        CallRestApi apiCaller = new CallRestApi();
        ReservationStatus status;
        status=apiCaller.checkReservationStatus();
        Intent intent = new Intent(SelectActivity.this, LockerListActivity.class);
        Intent intent2 = new Intent(SelectActivity.this,ReserveStateActivity.class);
        Log.e("test", status.result);
        if(status.result.equals("diffIP")){
            Log.e("Login Session", "다른 기기에서 로그인되었음" );
            Toast.makeText(SelectActivity.this, "다른 기기에서 로그인되어 종료합니다.", Toast.LENGTH_SHORT).show();
            apiCaller.logout();
            ActivityCompat.finishAffinity(SelectActivity.this);
            System.exit(0);
        }
        if(status.result.equals("idle")) {
            startActivity(intent);
        }
        else if(status.result.equals("reserved") || status.result.equals("using") || status.result.equals("overdue")){
            intent2.putExtra("result", status.result);
            intent2.putExtra("startdate", status.startdate);
            intent2.putExtra("enddate", status.enddate);
            intent2.putExtra("usinglockername", status.usinglockername);
            intent2.putExtra("location", status.location);
            if(status.result.equals("using"))
                intent2.putExtra("lockernum", status.lockernum);
            startActivity(intent2);
        }
        else
            Toast.makeText(SelectActivity.this, "unkown statement", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        imgBtnreserve =(ImageButton)findViewById(R.id.img_reserve) ;
        imgBtnotpcheck = (ImageButton)findViewById(R.id.img_otpcheck);
        imgBtnmyinfo = (ImageButton)findViewById(R.id.img_myinfo);
        imgBtnnotice = (ImageButton)findViewById(R.id.img_notice);
        myInfoRela = (RelativeLayout)findViewById(R.id.thirdRela);
        reserveRela = (RelativeLayout)findViewById(R.id.firstRela);
        OTPRela = (RelativeLayout)findViewById(R.id.secondRela);
        noticeRela = (RelativeLayout)findViewById(R.id.forthRela);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);


        startService(new Intent(this, ForcedTerminationService.class)); // 앱 강제종료 시 로그아웃하는 서비스

        CallRestApi apiCaller = new CallRestApi();
        apiCaller.setFCMToken();

        if(CurrentLoggedInID.reservePush.equals("client_overdue")){
            CurrentLoggedInID.reservePush="";
            onClickReserve(OTPRela);
        }else if(CurrentLoggedInID.reservePush.equals("notice")){
            Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
            startActivity(intent);
        }

        imgBtnreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReserve(v);
            }
        });
        reserveRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickReserve(v);
            }
        });
        imgBtnreserve.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchReserve(event, reserveRela);
            }
        });
        reserveRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                return onTouchReserve(event, reserveRela);
            }
        });
        OTPRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, otp_popup_Activity.class);
                Intent intent1 = new Intent(SelectActivity.this, OtpActivity.class);

                if(!mNfcAdapter.isEnabled()) {
                    startActivity(intent);
                }

                else startActivity(intent1);

            }
        });
        imgBtnotpcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, otp_popup_Activity.class);
                Intent intent1 = new Intent(SelectActivity.this, OtpActivity.class);

                if(!mNfcAdapter.isEnabled()) {
                    startActivity(intent);
                }
                else startActivity(intent1);
            }
        });
        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                isLoggedOutByButton=true;
                CallRestApi apiCaller = new CallRestApi();
                String result = apiCaller.logout();
                if(result.compareTo("success")==0) {
                    CurrentLoggedInID.isLoggedIn=false;
                    Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        imgBtnLogout.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
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
        noticeRela.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        imgBtnnotice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        noticeRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                return onTouchReserve(event, noticeRela);

            }
        });
        imgBtnnotice.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                return onTouchReserve(event, noticeRela);

            }
        });
        myInfoRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,EmailVerificationActivity.class);
                startActivity(intent);
            }
        });
        imgBtnmyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,EmailVerificationActivity.class);
                startActivity(intent);
            }
        });
        myInfoRela.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               return onTouchReserve(motionEvent, myInfoRela);
            }
        });
        imgBtnmyinfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchReserve(event, myInfoRela);
            }
        });
        OTPRela.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouchReserve(motionEvent, OTPRela);
            }
        });
        imgBtnotpcheck.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouchReserve(motionEvent, OTPRela);
            }
        });

    }
    @Override
    protected void onDestroy() {

        Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        CallRestApi apiCaller = new CallRestApi();
        apiCaller.logout();
        CurrentLoggedInID.isLoggedIn=false;
        CurrentLoggedInID.ID="";
        super.onDestroy();
    }
}