package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FixInfoActivity extends AppCompatActivity {
    ImageButton reservecheckbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_info);

        reservecheckbtn = (ImageButton)findViewById(R.id.reservecheckbtn);


        reservecheckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixInfoActivity.this, ReInfoCheckActivity.class);
                startActivity(intent);
            }
        });
    }
}