package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EmailVerificationActivity extends AppCompatActivity {

    private EditText edt_code;
    private Button btn_sendEmail;
    private ImageButton btn_verify;
    private TextView tv_notice;
    private TextView tv_remaintime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        edt_code=(EditText)findViewById(R.id.edt_code);
        btn_sendEmail=(Button)findViewById(R.id.btn_sendEmail);
        btn_verify=(ImageButton)findViewById(R.id.btn_verify);
        tv_notice=(TextView) findViewById(R.id.tv_notice);
        tv_remaintime=(TextView)findViewById(R.id.tv_remaintime);

        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallRestApi apiCaller=new CallRestApi();
                String result = apiCaller.sendVerifyingEmail();
                if(result.equals("diffIP")){
                    Log.e("Login Session", "다른 기기에서 로그인되었음" );
                    Toast.makeText(EmailVerificationActivity.this, "다른 기기에서 로그인되어 종료합니다.", Toast.LENGTH_SHORT).show();
                    moveTaskToBack(true);
                    finishAndRemoveTask();
                    System.exit(0);
                }
                else if(result.equals("success")) {
                    btn_verify.setVisibility(View.VISIBLE);
                    tv_notice.setVisibility(View.VISIBLE);
                    tv_remaintime.setVisibility(View.VISIBLE);
                    btn_sendEmail.setText("인증번호 재전송");
                }
            }
        });
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallRestApi apiCaller = new CallRestApi();
                String code=edt_code.getText().toString();
                if(code.isEmpty()){
                    Toast.makeText(EmailVerificationActivity.this, "인증번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                String result=apiCaller.verifyingCode(code);
                if(result.equals("expired")){
                    Toast.makeText(EmailVerificationActivity.this, "인증번호가 만료되었습니다. 인증번호를 재전송해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(result.equals("not match")){
                    Toast.makeText(EmailVerificationActivity.this, "인증번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(result.equals("diffIP")){
                    Log.e("Login Session", "다른 기기에서 로그인되었음" );
                    Toast.makeText(EmailVerificationActivity.this, "다른 기기에서 로그인되어 종료합니다.", Toast.LENGTH_SHORT).show();
                    moveTaskToBack(true);
                    finishAndRemoveTask();
                    System.exit(0);
                }
                else if(result.equals("success")){
                    Toast.makeText(EmailVerificationActivity.this, "인증되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmailVerificationActivity.this, FixInfoActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
}