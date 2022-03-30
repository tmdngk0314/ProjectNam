package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Reader;

public class ActivitySignUp extends AppCompatActivity {
    private ImageButton make_id;

    private RelativeLayout ReName;
    private RelativeLayout ReEmail;
    private RelativeLayout ReID;
    private RelativeLayout RePass;
    private RelativeLayout RePassCheck;

    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_id;
    private EditText edt_pw;
    private EditText edt_pwchk;

    private TextView tv_warning_name;
    private TextView tv_warning_email;
    private TextView tv_warning_id;
    private TextView tv_warning_pw;
    private TextView tv_warning_pwchk;

    private boolean available_name=false;
    private boolean available_email=false;
    private boolean available_id=false;
    private boolean available_pw=false;
    private boolean available_pwchk=false;


    boolean isAllAvailable(){
        if(available_name==true && available_email==true && available_id==true
        && available_pw==true && available_pwchk==true){
            return true;
        }
        else
            return false;
    }
    public  int countChar(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    boolean isAvailable_name(String str){
        if(str.length()>16 || str.length()<2){
            return false;
        }
        if(str!=null  && str.matches("[a-z|A-Z|가-힝|ㄱ-ㅎ|ㅏ-ㅣ| ]*")) {
            return true;
        }else {
            return false;
        }
    }
    boolean isAvailable_email(String str){
        if(str.length()<3){
            return false;
        }
        if(str!=null  && str.matches("[a-z|A-Z|0-9|@|.]*")) {
            int atSignIndex = str.indexOf('@');
            int dotIndex = str.indexOf('.');
            if(atSignIndex<0 || dotIndex<0) // .과 @가 포함되지 않았을 경우
                return false;
            else if(atSignIndex==0 || atSignIndex==str.length()) // @가 이메일 맨 처음이나 끝에 있을 경우
                return false;
            else if(countChar(str, '@')!=1) // @가 1개가 아닐 경우
                return false;
            else if(dotIndex==str.length()-1 || dotIndex<atSignIndex+2) // .의 위치가 바르지 않을 경우
                return false;
            else
                return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ReName=(RelativeLayout)findViewById(R.id.ReName);
        ReEmail=(RelativeLayout)findViewById(R.id.ReEmail);
        ReID=(RelativeLayout)findViewById(R.id.ReID);
        RePass=(RelativeLayout)findViewById(R.id.RePass);
        RePassCheck=(RelativeLayout)findViewById(R.id.RePassCheck);

        make_id=(ImageButton)findViewById(R.id.btnMakeID);
        edt_name=(EditText) findViewById(R.id.edt_name);
        edt_email=(EditText) findViewById(R.id.edt_email);
        edt_id=(EditText) findViewById(R.id.edt_id);
        edt_pw=(EditText) findViewById(R.id.edt_pw);
        edt_pwchk=(EditText) findViewById(R.id.edt_pwchk);

        tv_warning_name=(TextView)findViewById(R.id.tv_warning_name);
        tv_warning_email=(TextView)findViewById(R.id.tv_warning_email);
        tv_warning_id=(TextView)findViewById(R.id.tv_warning_id);
        tv_warning_pw=(TextView)findViewById(R.id.tv_warning_pw);
        tv_warning_pwchk=(TextView)findViewById(R.id.tv_warning_pwchk);

        make_id.setEnabled(false);



        // 이름 입력 EditText에 텍스트가 변경되었을 경우
        edt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String txt = editable.toString();
                int length = txt.length();
                if(length==0){
                    available_name=false;
                    ReName.setBackgroundResource(R.drawable.border_gray);
                    tv_warning_name.setVisibility(View.INVISIBLE);
                    make_id.setEnabled(false);
                }
                else if(!isAvailable_name(txt)){
                    available_name=false;
                    tv_warning_name.setVisibility(View.VISIBLE);
                    ReName.setBackgroundResource(R.drawable.border_red);
                    make_id.setEnabled(false);
                }
                else{
                    available_name=true;
                    tv_warning_name.setVisibility(View.INVISIBLE);
                    ReName.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true)
                        make_id.setEnabled(true);
                }

            }
        });

        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String txt = editable.toString();
                int length = txt.length();
                if(length==0){
                    ReEmail.setBackgroundResource(R.drawable.border_gray);
                    available_email=false;
                    tv_warning_email.setVisibility(View.INVISIBLE);
                    make_id.setEnabled(false);
                }
                else if(!isAvailable_email(txt)){
                    ReEmail.setBackgroundResource(R.drawable.border_red);
                    available_email=false;
                    tv_warning_email.setVisibility(View.VISIBLE);
                    make_id.setEnabled(false);
                }
                else{
                    available_email=true;
                    tv_warning_email.setVisibility(View.INVISIBLE);
                    ReEmail.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true)
                        make_id.setEnabled(true);
                }

            }
        });


    }
}