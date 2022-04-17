package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.TimePicker;

public class ReserveActivity extends AppCompatActivity {
    ImageButton shortbtn, longbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        shortbtn = findViewById(R.id.shortrent);
        longbtn = findViewById(R.id.longrent);

        shortbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveActivity.this, ReserveShortActivity.class);
                startActivity(intent);
                finish();
            }
        });

        longbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReserveActivity.this, ReserveLongActivity.class);
                startActivity(intent1);
                finish();
            }
        });



    }
}