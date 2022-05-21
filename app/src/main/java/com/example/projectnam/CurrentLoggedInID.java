package com.example.projectnam;

public class CurrentLoggedInID {
    public static String ID="";
    public static String name="";
    public static String email="";
    public static Boolean isLoggedIn=false;
    private static String token="";
    private static String fcmtoken="";

    public static String getFCMtoken() {
        return fcmtoken;
    }
    public static void setFCMToken(String input){
        fcmtoken=input;
    }

    public static String getAuthToken(){
        return token;
    }
    public static void resetAuthToken(){
        token="";
    }
    public static void setAuthToken(String newToken) {

        token= newToken;
    }


}
