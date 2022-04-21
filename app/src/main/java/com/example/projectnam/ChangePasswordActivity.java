package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText currentPass_Edt,newPass_Edt,newPassCheck_Edt;
    TextView newPasstext;
    SharedPreferences deviceInfo;
    boolean isAvailable_pw(String str){
        if(str.length()>16 || str.length()<8){
            return false;
        }
        if(str!=null  && str.matches("[a-z|A-Z|0-9|!|@|#|$|%|^|*|,|.|/|?]*")) {
            if(str.matches(".*[0-9].*")) {
                if (str.matches(".*[!|@|#|$|%|^|*|,|.|/|?].*"))
                    if(str.matches(".*[a-z|A-Z].*"))
                        return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPass_Edt = (EditText) findViewById(R.id.currentPass_edt);
        newPass_Edt = (EditText) findViewById(R.id.newPass_edt);
        newPassCheck_Edt = (EditText) findViewById(R.id.newpasscheck_edt);
        newPasstext = (TextView)findViewById(R.id.newPass_text);

        CallRestApi restApi = new CallRestApi();

        newPass_Edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String txt = editable.toString();
                int length = txt.length();
                String pwchk=newPassCheck_Edt.getText().toString();
                if(isAvailable_pw(txt)==false){
                    newPasstext.setVisibility(View.VISIBLE);
                }
                else newPasstext.setVisibility(View.INVISIBLE);

            }
        });



    }
}