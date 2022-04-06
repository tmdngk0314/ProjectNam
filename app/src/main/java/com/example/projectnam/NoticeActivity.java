package com.example.projectnam;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    int noticeCount=0;
    int pageValue;

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    private Integer[] index=new Integer[10];
    private String[] title=new String[10];
    private String[] date=new String[10];
    private String[] body=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        CallRestApi apiCaller = new CallRestApi();
        NoticeInfo noticeInfo = apiCaller.loadNotice(3);

        for(int i=9;i>=0;i--){
            noticeList.add(new Notice(noticeInfo.title[i],noticeInfo.date[i]));
        }

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);  //리스트 뷰에 해당 어뎁터 매칭

    }

}
