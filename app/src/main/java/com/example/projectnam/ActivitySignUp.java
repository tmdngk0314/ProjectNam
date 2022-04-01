package com.example.projectnam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
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

    boolean isAvailable_id(String str){
        if(str.length()>16 || str.length()<8){
            return false;
        }
        if(str!=null  && str.matches("[a-z|A-Z|0-9]*")) {
            if(str.matches(".*[a-z|A-Z].*"))
                if(str.matches(".*[0-9].*"))
                    return true;
        }
        return false;
    }
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
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else{
                    available_name=true;
                    tv_warning_name.setVisibility(View.INVISIBLE);
                    ReName.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true) {
                        make_id.setEnabled(true);
                        make_id.setBackgroundResource(R.drawable.account_creation);
                    }
                }

            }
        });

        // email EditText 텍스트 변경 시
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
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else if(!isAvailable_email(txt)){
                    ReEmail.setBackgroundResource(R.drawable.border_red);
                    available_email=false;
                    tv_warning_email.setVisibility(View.VISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else{
                    available_email=true;
                    tv_warning_email.setVisibility(View.INVISIBLE);
                    ReEmail.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true) {
                        make_id.setEnabled(true);
                        make_id.setBackgroundResource(R.drawable.account_creation);
                    }
                }

            }
        });

        edt_id.addTextChangedListener(new TextWatcher() {
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
                    ReID.setBackgroundResource(R.drawable.border_gray);
                    available_id=false;
                    tv_warning_id.setVisibility(View.INVISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else if(!isAvailable_id(txt)){
                    ReID.setBackgroundResource(R.drawable.border_red);
                    available_id=false;
                    tv_warning_id.setVisibility(View.VISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else{
                    available_id=true;
                    tv_warning_id.setVisibility(View.INVISIBLE);
                    ReID.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true) {
                        make_id.setEnabled(true);
                        make_id.setBackgroundResource(R.drawable.account_creation);
                    }
                }
            }
        });

        edt_pw.addTextChangedListener(new TextWatcher() {
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
                String pwchk=edt_pwchk.getText().toString();
                if(pwchk.length()>0 ){ // 비밀번호가 수정되었을 때 비밀번호 확인과 일치하는지 확인 필요
                    if(pwchk.compareTo(txt)!=0) {
                        RePassCheck.setBackgroundResource(R.drawable.border_red);
                        available_pwchk = false;
                        tv_warning_pwchk.setVisibility(View.VISIBLE);
                        make_id.setEnabled(false);
                        make_id.setBackgroundResource(R.drawable.account_creation_fail);
                    }
                    else{
                        available_pwchk=true;
                        tv_warning_pwchk.setVisibility(View.INVISIBLE);
                        RePassCheck.setBackgroundResource(R.drawable.border_gray);
                    }
                }
                if(length==0){
                    RePass.setBackgroundResource(R.drawable.border_gray);
                    available_pw=false;
                    tv_warning_pw.setVisibility(View.INVISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else if(!isAvailable_pw(txt)){
                    RePass.setBackgroundResource(R.drawable.border_red);
                    available_pw=false;
                    tv_warning_pw.setVisibility(View.VISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else{
                    available_pw=true;
                    tv_warning_pw.setVisibility(View.INVISIBLE);
                    RePass.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true) {
                        make_id.setEnabled(true);
                        make_id.setBackgroundResource(R.drawable.account_creation);
                    }
                }
            }
        });

        edt_pwchk.addTextChangedListener(new TextWatcher() {
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
                    RePassCheck.setBackgroundResource(R.drawable.border_gray);
                    available_pwchk=false;
                    tv_warning_pwchk.setVisibility(View.INVISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else if(txt.compareTo(edt_pw.getText().toString())!=0){
                    RePassCheck.setBackgroundResource(R.drawable.border_red);
                    available_pwchk=false;
                    tv_warning_pwchk.setVisibility(View.VISIBLE);
                    make_id.setEnabled(false);
                    make_id.setBackgroundResource(R.drawable.account_creation_fail);
                }
                else{
                    available_pwchk=true;
                    tv_warning_pwchk.setVisibility(View.INVISIBLE);
                    RePassCheck.setBackgroundResource(R.drawable.border_gray);
                    if(isAllAvailable()==true && isAvailable_pw(txt)) {
                        make_id.setEnabled(true);
                        make_id.setBackgroundResource(R.drawable.account_creation);
                    }
                }
            }
        });

        make_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallRestApi apiCaller = new CallRestApi();
                int result; // result가 0이면 회원가입 성공, -1이면 회원가입 실패
                result = apiCaller.newAccount(edt_name.getText().toString(), edt_email.getText().toString(),
                        edt_id.getText().toString(), edt_pw.getText().toString());
                if(result==200) {
                    Log.i("회원가입", Integer.toString(result)+":회원가입 성공");
                    finish();
                }
                else if(result==409){
                    Log.i("회원가입", Integer.toString(result)+":중복 아이디 존재");
                }
                else if(result==503){
                    Log.e("회원가입", Integer.toString(result)+":서버와 연결이 되지 않음");
                }
                else{
                    Log.e("회원가입",Integer.toString(result)+":알 수 없는 오류");
                }

            }
        });



    }
}