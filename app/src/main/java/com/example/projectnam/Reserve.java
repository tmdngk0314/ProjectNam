package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Reserve extends AppCompatActivity {
    ImageView imgBtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        imgBtnLogout = (ImageView)findViewById(R.id.imgBtnLogout);



        imgBtnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Reserve.this, SelectActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}