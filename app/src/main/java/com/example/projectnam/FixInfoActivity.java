package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FixInfoActivity extends AppCompatActivity {
    ImageButton reservecheckbtn, changePW;

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

        reservecheckbtn = (ImageButton)findViewById(R.id.reservecheckbtn);
        changePW = (ImageButton)findViewById(R.id.changePw);


        reservecheckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixInfoActivity.this, ReInfoCheckActivity.class);
                startActivity(intent);
            }
        });

        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}