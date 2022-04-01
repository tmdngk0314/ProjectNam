package com.example.projectnam;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRestApi {
    static int lastResponseCode=0;
    public JSONObject receivedJSON;
    public void getRestAPI(String link){


    }
    public void postRestAPI(JSONObject sendJSON, String link){
        JSONObject a;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lastResponseCode=0;
                    URL url = new URL("http://192.168.230.175:5000/"+link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);
                    OutputStream outputStream;
                    outputStream = conn.getOutputStream();
                    outputStream.write(sendJSON.toString().getBytes());
                    int response = conn.getResponseCode();
                    Log.i("responseCode???", Integer.toString(response));
                    lastResponseCode=response;
                    String responseMessage = conn.getResponseMessage();
                    System.out.println("----responseMessage----- : "+responseMessage);

                    InputStream is = conn.getInputStream();
                    StringBuilder builder = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line;
                    while((line=reader.readLine())!=null) {
                        builder.append(line);
                    }
                    String result = builder.toString();
                    Log.i("json 결과 ", result);
                    receivedJSON = new JSONObject(result);
                    //receivedJSON.get("otpkey");
                    conn.disconnect();

                }
                catch(Exception e){
                    Log.e("REST API", "POST method failed: " + e.getMessage());
                    e.printStackTrace();
                    lastResponseCode=503;
                }
            }
        });
        thread.start();
        while(thread.isAlive());
    }



    public int newAccount(String name, String email, String id, String pw){
        JSONObject info = new JSONObject();
        try {
            info.put("name", name);
            info.put("email", email);
            info.put("id", id);
            info.put("pw", pw);
            postRestAPI(info, "newaccount");
            return lastResponseCode;
        } catch (JSONException e) {
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return -2;
        }
    }

    public int login(String id, String pw){
        JSONObject info = new JSONObject();
        try{
            info.put("id", id);
            info.put("pw", pw);
            postRestAPI(info, "login/client");
            return lastResponseCode;
        }
        catch(JSONException e){
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return -2;
        }
    }

}
