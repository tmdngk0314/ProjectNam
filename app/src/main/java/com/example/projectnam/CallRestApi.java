package com.example.projectnam;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRestApi {
    static int lastResponseCode=0;
    public JSONObject receivedJSONObject;
    public JSONArray receivedJSONArray;
    public void getRestAPI(String link){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lastResponseCode = 0;
                try {
                    URL url = new URL("http://192.168.230.175:5000/"+link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoInput(true);
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);
                    InputStream is = conn.getInputStream();

                    // Get the stream
                    StringBuilder builder = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    // Set the result
                    String result = builder.toString();
                    Log.i("json 결과", result);
                    JSONObject json = null;
                    json = new JSONObject(result);
                    receivedJSONObject = json;
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        while(thread.isAlive());

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
                    Object json= new JSONTokener(result).nextValue();
                    if(json instanceof JSONObject)
                        receivedJSONObject = new JSONObject(result);
                    else if(json instanceof JSONArray)
                        receivedJSONArray = new JSONArray(result);
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
    public String loadNotice(int page){
        JSONObject jsonPage=new JSONObject();
        Integer[] index=new Integer[10];
        String[] title=new String[10];
        String[] date=new String[10];
        String[] body=new String[10];
        try{
            jsonPage.put("page", 3);
            postRestAPI(jsonPage,"notice/load");

            for(int i=0; i<receivedJSONArray.length(); i++){
                index[i]=(Integer)receivedJSONArray.getJSONArray(i).get(0);
                title[i]=(String)receivedJSONArray.getJSONArray(i).get(1);
                date[i]=(String)receivedJSONArray.getJSONArray(i).get(2);
                body[i]=(String)receivedJSONArray.getJSONArray(i).get(3);
                Log.e("JSONArray로그", Integer.toString(index[i])+title[i]+date[i]+body[i]);
            }


            return "finish";
        }
        catch(Exception e){
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return "JSONException";
        }
    }


    public String newAccount(SharedPreferences deviceInfo, String name, String email, String id, String pw){
        JSONObject info = new JSONObject();
        try {
            info.put("name", name);
            info.put("email", email);
            info.put("id", id);
            info.put("pw", pw);
            postRestAPI(info, "newaccount");
            String result= receivedJSONObject.getString("result");
            switch(result) {
                case "success":
                    SharedPreferences.Editor editor = deviceInfo.edit();
                    editor.putString(id, receivedJSONObject.getString("otpkey"));
                    editor.commit();
            }
            return result;


        } catch (JSONException e) {
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return "JSONException";
        }
    }

    public String login(String id, String pw){
        JSONObject info = new JSONObject();
        try{
            info.put("id", id);
            info.put("pw", pw);
            postRestAPI(info, "login/client");
            String result = receivedJSONObject.getString("result");
            return result;
        }
        catch(JSONException e){
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return "unknown";
        }
    }

}
