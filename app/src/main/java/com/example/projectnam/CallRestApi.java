package com.example.projectnam;

import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRestApi {
    static String lastResponseMessage;
    public static String postRestAPI(JSONObject sendJSON){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.230.175:5000/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    OutputStream outputStream;
                    outputStream = conn.getOutputStream();
                    outputStream.write(sendJSON.toString().getBytes());

                    int response = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();
                    Log.i("Post Response:", responseMessage);
                    conn.disconnect();
                    lastResponseMessage=responseMessage;
                }
                catch(Exception e){
                    Log.e("REST API", "POST method failed: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
        return lastResponseMessage;
    }



    public static int newAccount(String name, String id, String pw, String email){
        return 0;
    }


}
