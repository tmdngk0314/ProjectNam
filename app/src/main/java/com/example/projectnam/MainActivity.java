package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ImageButton imgok;
    TextView new_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ProjectNam);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgok = (ImageButton)findViewById(R.id.btnOk);
        new_account= (TextView)findViewById(R.id.new_account);

        new_account.setPaintFlags(new_account.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        imgok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent);
                finish();
            }
        });
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });
    }




}