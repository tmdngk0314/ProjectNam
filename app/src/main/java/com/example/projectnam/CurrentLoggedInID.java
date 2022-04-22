package com.example.projectnam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CurrentLoggedInID {
    public static String ID="";
    public static String name="";
    public static String email="";
    public static Boolean isLoggedIn=false;
    private static String token="";
    public static String getAuthToken(){
        return token;
    }
    public static void resetAuthToken(){
        token="";
    }
    public static void setAuthToken() {
        String newToken = null;
        try {
            SecureRandom secureRandom
                    = SecureRandom.getInstance("SHA1PRNG");
            MessageDigest digest
                    = MessageDigest.getInstance("SHA-256");
            secureRandom
                    .setSeed(secureRandom.generateSeed(128));
            newToken =
                    new String(
                            digest.digest(
                                    (secureRandom.nextInt() + "")
                                            .getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        token= newToken;
    }


}
