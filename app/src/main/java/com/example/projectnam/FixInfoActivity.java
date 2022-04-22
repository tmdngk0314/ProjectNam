package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FixInfoActivity extends AppCompatActivity {
    RelativeLayout ChangePWrela;
    TextView textname;
    TextView textemail;
    @Override
    protected void onDestroy() {
        CallRestApi apiCaller = new CallRestApi();
        apiCaller.unverifyingCode();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_info);

        textname=(TextView)findViewById(R.id.textname);
        textemail=(TextView)findViewById(R.id.textEmail);
        textname.setText(CurrentLoggedInID.name);
        textemail.setText(CurrentLoggedInID.email);
        ChangePWrela = (RelativeLayout)findViewById(R.id.ChangePWrela);


        ChangePWrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}