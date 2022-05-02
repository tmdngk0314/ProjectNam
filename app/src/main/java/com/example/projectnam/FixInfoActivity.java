package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FixInfoActivity extends AppCompatActivity {
    RelativeLayout ChangePWrela;
    RelativeLayout Rela_reissueOTP, Rela_deleteAccount;
    TextView textname;
    TextView textemail;
    SharedPreferences deviceInfo;
    @Override
    protected void onDestroy() {
        CallRestApi apiCaller = new CallRestApi();
        apiCaller.unverifyingCode();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_info);

        textname=(TextView)findViewById(R.id.textname);
        textemail=(TextView)findViewById(R.id.textEmail);
        textname.setText(CurrentLoggedInID.name);
        textemail.setText(CurrentLoggedInID.email);
        ChangePWrela = (RelativeLayout)findViewById(R.id.ChangePWrela);
        Rela_reissueOTP=(RelativeLayout)findViewById(R.id.OTPReissurela);
        Rela_deleteAccount = (RelativeLayout)findViewById(R.id.AccountDelrela);


        deviceInfo=getSharedPreferences("accountOTP", 0);

        ChangePWrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        ChangePWrela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        ChangePWrela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        ChangePWrela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });

        Rela_reissueOTP.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Rela_reissueOTP.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        Rela_reissueOTP.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });

        Rela_deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixInfoActivity.this, delaccount_pop_up_Activity.class);
                startActivity(intent);
            }
        });

        Rela_deleteAccount.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Rela_deleteAccount.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        Rela_deleteAccount.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });
        Rela_reissueOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallRestApi apiCaller=new CallRestApi();
                String result=apiCaller.reissuanceotp(deviceInfo, CurrentLoggedInID.ID);
                if(result.equals("success")){
                    Toast.makeText(FixInfoActivity.this, "OTP Key가 갱신되었습니다.", Toast.LENGTH_SHORT).show();
                }else if(result.equals("diffIP")){
                    Log.e("Login Session", "다른 기기에서 로그인되었음" );
                    Toast.makeText(FixInfoActivity.this, "다른 기기에서 로그인되어 종료합니다.", Toast.LENGTH_SHORT).show();
                    apiCaller.logout();
                    ActivityCompat.finishAffinity(FixInfoActivity.this);
                    System.exit(0);
                }else if(result.equals("not verified")){
                    Toast.makeText(FixInfoActivity.this, "본인인증이 진행되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    apiCaller.unverifyingCode();
                    finish();
                }else{
                    Toast.makeText(FixInfoActivity.this, "server error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}