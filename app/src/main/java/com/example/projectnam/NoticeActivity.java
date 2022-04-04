package com.example.projectnam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    int noticeCount=0;
    int pageValue;


    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("OTP 활용법","2022-04-01"));
        noticeList.add(new Notice("NFC 태그란?","2022-04-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));
        noticeList.add(new Notice("노성우 생일","2022-02-01"));

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);  //리스트 뷰에 해당 어뎁터 매칭

        CallRestApi apiCaller = new CallRestApi();
        apiCaller.loadNotice(1);
        //notice/load


        /*
        try {
            notice.getRestAPI("notice/loadcount");
            noticeCount = notice.receivedJSON.getInt("maxindex");
            Log.i("loadCount ", Integer.toString(noticeCount));



            String asd[][] = new String[noticeCount][4];
            JSONObject page = new JSONObject();
            //notice.postRestAPI(new JSONObject(Integer.toString(1)),"notice/load");
            pageValue=1;

            page.put("page", pageValue);

            notice.postRestAPI(page,"notice/load");
            for(int i=0;i<noticeCount;i++){
                asd[i][0] = Integer.toString(noticeCount-i - (pageValue-1)*10);
                asd[i][1] = notice.receivedJSON.getString("body");
                asd[i][2] = notice.receivedJSON.getString("date");
                asd[i][3] = notice.receivedJSON.getString("title");
                Log.i("INDEX : ",asd[i][0]);
                Log.i("BODY : ",asd[i][1]);
                Log.i("DATE : ",asd[i][2]);
                Log.i("TITLE : ",asd[i][3]);
            }

            // title body date index

            Log.i("Heool","Hello22222222222222");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/


    }

}
