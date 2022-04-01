package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    ImageButton imgok;
    TextView new_account;
    EditText edt_inputid;
    EditText edt_inputpw;
    SharedPreferences deviceInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ProjectNam);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgok = (ImageButton)findViewById(R.id.btnOk);
        new_account= (TextView)findViewById(R.id.new_account);
        edt_inputid =(EditText) findViewById(R.id.edt_inputid);
        edt_inputpw=(EditText)findViewById(R.id.edt_inputpw);
        new_account.setPaintFlags(new_account.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        deviceInfo = getSharedPreferences("accountOTP", 0);


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
                            Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case "no match":
                            Log.e("로그인", "아이디/비밀번호 불일치");
                            break;
                        default:
                            Log.e("로그인", "unknown:알 수 없는 오류");
                    }
                }
            }
        });
        imgok.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        imgok.setBackgroundResource(R.drawable.ok_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        imgok.setBackgroundResource(R.drawable.ok);
                        return false;
                    default: return false;
                }

            }
        });
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });
        new_account.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                float curX = event.getX();
                float curY = event.getY();
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




}