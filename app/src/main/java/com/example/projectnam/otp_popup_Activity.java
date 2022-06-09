package com.example.projectnam;

/*
 NFC 기능 미구현으로 더 이상 사용되지 않음.


*/
 /*
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class otp_popup_Activity extends AppCompatActivity {
    NfcAdapter mNfcAdapter;
    Button btnNfc,btnOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_popup);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        btnNfc = (Button) findViewById(R.id.nfconBtn);
        btnOtp = (Button) findViewById(R.id.otpBtn);

        btnNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mNfcAdapter.isEnabled()){
                    startActivity(new Intent("android.settings.NFC_SETTINGS"));
                }
            }
        });

        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(otp_popup_Activity.this, OtpActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}*/