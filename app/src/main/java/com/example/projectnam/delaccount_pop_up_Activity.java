package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class delaccount_pop_up_Activity extends AppCompatActivity {
    Button okbtn,cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delaccount_pop_up);

        okbtn = (Button) findViewById(R.id.okBtn);
        cancelbtn = (Button) findViewById(R.id.cancelBtn);


    }
}