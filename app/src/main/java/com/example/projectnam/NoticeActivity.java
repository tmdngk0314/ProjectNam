package com.example.projectnam;

import static android.view.View.VISIBLE;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private int page=1;

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

    private RelativeLayout pageRelative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        pageRelative = findViewById(R.id.pageRelative);


        PageChangeActivity pageChange = new PageChangeActivity(pageBtn);



        for(int i=0;i<7;i++) pageBtn[i] = (Button)findViewById(pageBtnName[i]);
        CallRestApi apiCaller = new CallRestApi();
        try {
            apiCaller.getRestAPI("notice/loadcount");
            noticeMax = apiCaller.receivedJSONObject.getInt("max");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NoticeInfo noticeInfo = apiCaller.loadNotice(page);

        //for(int i=9;i>=0;i--) noticeList.add(new Notice(noticeInfo.title[i],noticeInfo.date[i]));
        for(int i=9;i>=0;i--){
            noticeList.add(new Notice(noticeInfo.title[i],noticeInfo.date[i]));
            Log.e(noticeInfo.title[i], noticeInfo.date[i]);
        }

        pageChange.setPage(1,noticeMax);
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);  //리스트 뷰에 해당 어뎁터 매칭
/*
        for(int i=1;i<6;i++){
            pageBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = findViewById(v.getId());

                    int Page = Integer.parseInt(btn.getText().toString());
                    try {
                        apiCaller.getRestAPI("notice/loadcount");
                        noticeMax = apiCaller.receivedJSONObject.getInt("max");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    pageChange.setPage(Page,noticeMax);
                }
            });
        }*/



    }

}
