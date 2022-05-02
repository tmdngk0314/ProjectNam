package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FixInfoActivity extends AppCompatActivity {
    RelativeLayout ChangePWrela,AccountdelRela,OTPReissurela;
    TextView textname;
    TextView textemail;
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
        AccountdelRela = (RelativeLayout)findViewById(R.id.AccountDelrela);
        OTPReissurela = (RelativeLayout)findViewById(R.id.OTPReissurela);


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

        OTPReissurela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        OTPReissurela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        OTPReissurela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });

        AccountdelRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixInfoActivity.this, delaccount_pop_up_Activity.class);
                startActivity(intent);
            }
        });

        AccountdelRela.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        AccountdelRela.setBackgroundResource(R.drawable.select_box_touch);
                        return false;
                    case MotionEvent.ACTION_UP:
                        AccountdelRela.setBackgroundResource(R.drawable.select_box);
                        return false;
                    default: return false;
                }

            }
        });
    }
}