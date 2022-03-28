package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectActivity extends AppCompatActivity {
    ImageButton imgBtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);

        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}