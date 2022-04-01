package com.example.projectnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {
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



    }

}
