package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText currentID_Edt,currentPass_Edt,newPass_Edt,newPassCheck_Edt;
    SharedPreferences deviceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentID_Edt = (EditText) findViewById(R.id.currentID_edt);
        currentPass_Edt = (EditText) findViewById(R.id.currentPass_edt);
        newPass_Edt = (EditText) findViewById(R.id.newPass_edt);
        newPassCheck_Edt = (EditText) findViewById(R.id.newpasscheck_edt);

        CallRestApi restApi = new CallRestApi();



    }
}