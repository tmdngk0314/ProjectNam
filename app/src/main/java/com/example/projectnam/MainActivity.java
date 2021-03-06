package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity {
    ImageButton imgok;
    TextView new_account;
    EditText edt_inputid;
    EditText edt_inputpw;
    SharedPreferences deviceSettings;
    Boolean isSaved=false;
    CheckBox chk_saveAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ProjectNam);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgok = (ImageButton)findViewById(R.id.btnOk);
        new_account= (TextView)findViewById(R.id.new_account);
        edt_inputid =(EditText) findViewById(R.id.edt_inputid);
        edt_inputpw=(EditText)findViewById(R.id.edt_inputpw);
        chk_saveAccount=(CheckBox)findViewById(R.id.chk_saveaccount);
        new_account.setPaintFlags(new_account.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // 푸시 알림 토큰 가져오기
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);
                CurrentLoggedInID.setFCMToken(newToken);
            }
        });




        deviceSettings = getSharedPreferences("deviceSettings", 0);
        SharedPreferences.Editor editor = deviceSettings.edit();
        isSaved=deviceSettings.getBoolean("isSaved", false);
        if(isSaved==true){
            chk_saveAccount.setChecked(true);
            String id=deviceSettings.getString("savedID", "");
            String pw=deviceSettings.getString("savedPW", "");
            edt_inputid.setText(id);
            edt_inputpw.setText(pw);
            if(SelectActivity.isLoggedOutByButton==false) {
                CallRestApi apiCaller = new CallRestApi();
                String result = apiCaller.login(id, pw);
                switch (result) {
                    case "success":
                        Log.i("로그인", "로그인 성공");
                        CurrentLoggedInID.isLoggedIn = true;
                        Toast.makeText(MainActivity.this, "반갑습니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case "no match":
                        Toast.makeText(MainActivity.this, "아이디/패스워드 불일치", Toast.LENGTH_SHORT).show();
                        Log.e("로그인", "아이디/비밀번호 불일치");
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "서버 연결 오류", Toast.LENGTH_SHORT).show();
                        Log.e("로그인", "unknown:알 수 없는 오류");
                }
            }
        }
        SelectActivity.isLoggedOutByButton=false;

        chk_saveAccount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isSaved=b;
            }
        });

        imgok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String input_id=edt_inputid.getText().toString();
                String input_pw=edt_inputpw.getText().toString();
                if(input_id.length()>0 && input_pw.length()>0){
                    CallRestApi apiCaller = new CallRestApi();
                    String result = apiCaller.login(input_id, input_pw);
                    switch(result){
                        case "success":
                            Log.i("로그인", "로그인 성공");


                            if(isSaved==true) {
                                editor.putString("savedID", input_id);
                                editor.putString("savedPW", input_pw);
                                editor.putBoolean("isSaved", true);
                                editor.commit();
                            }else{
                                editor.putString("savedID", "");
                                editor.putString("savedPW", "");
                                editor.putBoolean("isSaved", false);
                                editor.commit();
                            }
                            CurrentLoggedInID.isLoggedIn=true;
                            Toast.makeText(MainActivity.this, "반갑습니다!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case "no match":
                            Toast.makeText(MainActivity.this, "아이디/패스워드 불일치", Toast.LENGTH_SHORT).show();
                            Log.e("로그인", "아이디/비밀번호 불일치");
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "서버 연결 오류", Toast.LENGTH_SHORT).show();
                            Log.e("로그인", "unknown:알 수 없는 오류");
                    }
                }
            }
        });
        imgok.setOnTouchListener(new OkTouch());
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });
        new_account.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        new_account.setTextColor(Color.GRAY);
                        return false;
                    case MotionEvent.ACTION_UP:
                        new_account.setTextColor(Color.BLACK);
                        return false;
                    default: return false;
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        if(isSaved==false){
            SharedPreferences.Editor editor=deviceSettings.edit();
            editor.putBoolean("isSaved", false);
            editor.putString("savedID", "");
            editor.putString("savedPW", "");
            editor.commit();
        }
        super.onDestroy();
    }
}