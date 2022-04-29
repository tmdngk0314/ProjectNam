package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReserveShortResultActivity extends AppCompatActivity {

    int year,month,day;
    TextView  usedate,tv_location, tv_lockername ;
    ImageButton ok;
    String location,lockername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_short_result);

        usedate = (TextView)findViewById(R.id.usedate);
        ok = (ImageButton)findViewById(R.id.nextBtn);
        tv_location=(TextView)findViewById(R.id.tv_location);
        tv_lockername=(TextView)findViewById(R.id.tv_lockername);

        year = getIntent().getExtras().getInt("년");
        month = getIntent().getExtras().getInt("달");
        day = getIntent().getExtras().getInt("일");
        location=getIntent().getExtras().getString("location");
        lockername=getIntent().getExtras().getString("lockername");

        Log.e("short locker name -",lockername);
        Log.e("short location -",location);


        usedate.setText(year+"."+month+"."+day);
        tv_location.setText(location);
        tv_lockername.setText(lockername);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok.setOnTouchListener(new OkTouch());
    }
}