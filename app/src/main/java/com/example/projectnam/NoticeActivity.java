package com.example.projectnam;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private int noticeMax;
    private int pageValue = 1;

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    private Integer[] index = new Integer[10];
    private String[] title = new String[10];
    private String[] date = new String[10];
    private String[] body = new String[10];

    private Button[] pageBtn = new Button[7];

    private int[] pageBtnName = {
            R.id.pageBtnLeft, R.id.pageBtn1, R.id.pageBtn2, R.id.pageBtn3,
            R.id.pageBtn4, R.id.pageBtn5, R.id.pageBtn6};

    private NoticeInfo noticeInfo = new NoticeInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        PageChangeActivity pageChange = new PageChangeActivity(pageBtn);

        for (int i = 0; i < 7; i++) pageBtn[i] = (Button) findViewById(pageBtnName[i]);

        CallRestApi apiCaller = new CallRestApi();

        try {
            apiCaller.getRestAPI("notice/loadcount");
            noticeMax = apiCaller.receivedJSONObject.getInt("maxindex");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        noticeInfo = apiCaller.loadNotice(pageValue);

        for (int i = 9; i >= 0; i--)
            noticeList.add(new Notice(noticeInfo.title[i], noticeInfo.date[i]));

        if(noticeInfo.result.equals("diffIP")){
            Log.e("Login Session", "다른 기기에서 로그인되었음" );
            Intent intent = new Intent(NoticeActivity.this, MainActivity.class);
            moveTaskToBack(true);
            finishAndRemoveTask();
            System.exit(0);
        }

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);  //리스트 뷰에 해당 어뎁터 매칭

        pageChange.setPage(pageValue, noticeMax);

        for (int i = 1; i < 6; i++) {
            pageBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pageComp;
                    Button btn = findViewById(v.getId());
                    pageComp = Integer.parseInt(btn.getText().toString());
                    if (pageComp != pageValue) {
                        pageValue = pageComp;
                        Log.e("CLICK PAGE ", Integer.toString(pageValue));
                        try {

                            apiCaller.getRestAPI("notice/loadcount");
                            noticeMax = apiCaller.receivedJSONObject.getInt("max");
                            noticeInfo = apiCaller.loadNotice(pageValue);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pageChange.setPage(pageValue, noticeMax);
                        noticeListView.smoothScrollToPositionFromTop(0, 10, 300);
                    }
                }
            });
        }
        pageBtn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageValue % 5 == 0) pageValue += 1;
                else pageValue = pageValue + 6 - (pageValue % 5);

                try {

                    apiCaller.getRestAPI("notice/loadcount");
                    noticeMax = apiCaller.receivedJSONObject.getInt("max");
                    noticeInfo = apiCaller.loadNotice(pageValue);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pageChange.setPage(pageValue, noticeMax);
                noticeListView.smoothScrollToPositionFromTop(0, 10, 300);
            }
        });
        pageBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageValue % 5 == 0) pageValue -= 5;
                else pageValue = pageValue - (pageValue % 5);

                try {

                    apiCaller.getRestAPI("notice/loadcount");
                    noticeMax = apiCaller.receivedJSONObject.getInt("max");
                    noticeInfo = apiCaller.loadNotice(pageValue);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pageChange.setPage(pageValue, noticeMax);
                noticeListView.smoothScrollToPositionFromTop(0, 10, 300);

            }
        });


    }
}
