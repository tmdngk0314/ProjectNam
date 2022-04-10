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
    public JSONObject receivedJSONObject=new JSONObject();
    public JSONArray receivedJSONArray;
    public void getRestAPI(String link){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lastResponseCode = 0;
                try {
                    URL url = new URL("http://192.168.0.101:5000/"+link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoInput(true);
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);
                    InputStream is = conn.getInputStream();

                    // Get the stream 넘어오는 결과 값들을 저장
                    StringBuilder builder = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    // 해당 InputStream에 있는 내용들을 버퍼에 담아서 읽을 수 있도록 한다.
                    String line;
                    //String형 line 변수를 만들고 하나씩 불러와서  문자열 형태로 저장.
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
        try {
            sendJSON.put("id", CurrentLoggedInID.ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lastResponseCode=0;
                    URL url = new URL("http://192.168.0.101:5000/"+link);
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
    public NoticeInfo loadNotice(int page){
        JSONObject jsonPage=new JSONObject();
        Integer[] index=new Integer[10];
        String[] title=new String[10];
        String[] date=new String[10];
        String[] body=new String[10];
        try{
            jsonPage.put("page", page);
            postRestAPI(jsonPage,"notice/load");
            try {
                if (receivedJSONObject.getString("result").equals("diffIP")) {
                    NoticeInfo info = new NoticeInfo();
                    info.result = "diffIP";
                    return info;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            for(int i=0; i<receivedJSONArray.length(); i++) {
                index[i] = (Integer) receivedJSONArray.getJSONArray(i).get(0);
                title[i] = (String) receivedJSONArray.getJSONArray(i).get(1);
                date[i] = (String) receivedJSONArray.getJSONArray(i).get(2);
                body[i] = (String) receivedJSONArray.getJSONArray(i).get(3);
                Log.e("JSONArray로그", Integer.toString(index[i]) + title[i] + date[i] + body[i]);
            }
            NoticeInfo info = new NoticeInfo();
            info.result="success";
            info.setIndex(index);
            info.setTitle(title);
            info.setDate(date);
            info.setBody(body);

            return info;
        }
        catch(Exception e){
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return new NoticeInfo();
        }
    }


    public String newAccount(SharedPreferences deviceInfo, String name, String email, String id, String pw){
        JSONObject info = new JSONObject();
        try {
            info.put("name", name);
            info.put("email", email);
            info.put("newId", id);
            info.put("pw", pw);
            postRestAPI(info, "newaccount");
            String result;
            result= receivedJSONObject.getString("result");
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
            CurrentLoggedInID.ID=id;
            postRestAPI(info, "client/login");
            String result="None";
            if(lastResponseCode==200) {
                result = receivedJSONObject.getString("result");
                if(result.compareTo("success")==0){
                    CurrentLoggedInID.ID=id;
                }
            }
            return result;
        }
        catch(JSONException e){
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return "unknown";
        }
    }
    public String logout(){
        JSONObject info = new JSONObject();
        try{
            postRestAPI(info, "client/logout");
            String result="None";
            if(lastResponseCode==200) {
                result = receivedJSONObject.getString("result");
                if(result.compareTo("success")==0){
                    CurrentLoggedInID.ID="";
                    CurrentLoggedInID.isLoggedIn=false;
                }
            }
            return result;
        }
        catch(JSONException e){
            Log.i("JSONException", "failed to put json data:"+e.getMessage());
            e.printStackTrace();
            return "unknown";
        }
    }

}
