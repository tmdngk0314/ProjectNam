package com.example.projectnam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private int noticeMax=0;
    private int pageValue;

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    private Integer[] index=new Integer[10];
    private String[] title=new String[10];
    private String[] date=new String[10];
    private String[] body=new String[10];

    private Button[] pageBtn=new Button[7];

    private int[] pageBtnName = {
            R.id.pageBtnLeft, R.id.pageBtn1, R.id.pageBtn2, R.id.pageBtn3,
            R.id.pageBtn4, R.id.pageBtn5, R.id.pageBtn6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        for(int i=0;i<7;i++) pageBtn[i] = (Button)findViewById(pageBtnName[i]);
        CallRestApi apiCaller = new CallRestApi();
        try {
            apiCaller.getRestAPI("notice/loadcount");
            noticeMax = apiCaller.receivedJSONObject.getInt("max");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NoticeInfo noticeInfo = apiCaller.loadNotice(1);
        if(noticeInfo.result.equals("diffIP")){
            Log.e("Login Session", "다른 기기에서 로그인되었음" );
            Intent intent = new Intent(NoticeActivity.this, MainActivity.class);
            moveTaskToBack(true);
            finishAndRemoveTask();
            System.exit(0);
        }



        for(int i=9;i>=0;i--) noticeList.add(new Notice(noticeInfo.title[i],noticeInfo.date[i]));


        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);  //리스트 뷰에 해당 어뎁터 매칭

    }

}
