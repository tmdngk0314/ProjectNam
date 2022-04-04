package com.example.projectnam;

import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ManageLoginToken {
    public static String loginToken;
    public String generateLoginToken(){
        Random random = new Random();
        random.setSeed(new Date().getTime());
        String LoginToken = "";
        for(int i=0 ;i<32;i++) {
            LoginToken = LoginToken + Integer.toString(random.nextInt());
        }
        Log.e("생성된 토큰", LoginToken);
        return LoginToken;
    }
}
