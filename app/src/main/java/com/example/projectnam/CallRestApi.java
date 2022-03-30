package com.example.projectnam;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRestApi {
    static int lastResponseCode=0;
    public static void postRestAPI(JSONObject sendJSON, String link){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.230.175:5000/"+link);
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
                    Log.i("responseCode", Integer.toString(response));
                    String responseMessage = conn.getResponseMessage();
                    conn.disconnect();
                    lastResponseCode=lastResponseCode;
                }
                catch(Exception e){
                    Log.e("REST API", "POST method failed: " + e.getMessage());
                    e.printStackTrace();
                    lastResponseCode=0;
                }
            }
        }).start();
    }



    public static int newAccount(String name, String email, String id, String pw){
        JSONObject info = new JSONObject();
        try {
            info.put("name", name);
            info.put("email", email);
            info.put("id", id);
            info.put("pw", pw);
            postRestAPI(info, "newaccount");
            Log.i("회원가입 lastResponseCode", Integer.toString(lastResponseCode));
            if(lastResponseCode==200) // 회원가입 성공
                return 0;
            else                       // 회원가입 실패
                return -1;
        } catch (JSONException e) {
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

}
