package com.example.projectnam;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private int noticeMax;
    private int pageValue = 1;
    private int pageOffset=10;

    public ListView noticeListView;
    private NoticeListAdapter adapter;

    private Integer[] index = new Integer[10];
    private String[] title = new String[10];
    private String[] date = new String[10];
    private String[] body = new String[10];

    public Button[] pageBtn = new Button[7];

    private int[] pageBtnName = {
            R.id.pageBtnLeft, R.id.pageBtn1, R.id.pageBtn2, R.id.pageBtn3,
            R.id.pageBtn4, R.id.pageBtn5, R.id.pageBtn6};

    public NoticeInfo noticeInfo = new NoticeInfo();

    public TextView noticeTitle, noticeDate, noticeBody;
    public RelativeLayout noticeRelative;
    public ImageButton noticeExitBtn, goSelectAct;

    CallRestApi apiCaller = new CallRestApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeTitle= (TextView)findViewById(R.id.noticeTitle);
        noticeDate = (TextView)findViewById(R.id.noticeDate);
        noticeBody = (TextView)findViewById(R.id.noticeBody);
        noticeRelative = (RelativeLayout)findViewById(R.id.noticeRelative);
        noticeExitBtn = (ImageButton)findViewById(R.id.noticeExit);
        ItemClickListener itemClickListener = new ItemClickListener(this);
        PageChangeActivity pageChange = new PageChangeActivity(pageBtn);

        goSelectAct = (ImageButton)findViewById(R.id.goSelectAct) ;

        for (int i = 0; i < 7; i++) pageBtn[i] = (Button) findViewById(pageBtnName[i]);



        try {
            apiCaller.getRestAPI("notice/loadcount");
            noticeMax = apiCaller.receivedJSONObject.getInt("maxindex");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        noticeInfo = apiCaller.loadNotice(pageValue);

        if(noticeInfo.result.equals("diffIP")){
            Log.e("Login Session", "?????? ???????????? ??????????????????" );
            Toast.makeText(this, "?????? ???????????? ??????????????? ???????????????.", Toast.LENGTH_SHORT).show();
            ActivityCompat.finishAffinity(NoticeActivity.this);
            System.exit(0);
        }

        if( noticeMax/10 == pageValue-1) pageOffset = noticeMax - (pageValue-1)*10;
        else pageOffset = 10;
        NoticeInfo swap = new NoticeInfo();
        for(int j=0;j<pageOffset;j++){
            swap.title[j] = noticeInfo.title[9-j-(10-pageOffset)];
            swap.date[j] = noticeInfo.date[9-j-(10-pageOffset)];
            swap.body[j] = noticeInfo.body[9-j-(10-pageOffset)];
        }
        noticeInfo = swap;

        adapter = new NoticeListAdapter(getApplicationContext(), noticeInfo, pageValue, pageOffset, noticeMax);
        noticeListView.setAdapter(adapter);  //????????? ?????? ?????? ????????? ??????

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
                        try {
                            apiCaller.getRestAPI("notice/loadcount");
                            noticeMax = apiCaller.receivedJSONObject.getInt("maxindex");

                        } catch (Exception e) {
                            Log.e("Notice Activity","????????? ?????? ?????? ??????");
                            e.printStackTrace();
                        }
                        noticeInfo = apiCaller.loadNotice(pageValue);

                        if( noticeMax/10 == pageValue-1) pageOffset = noticeMax - (pageValue-1)*10;
                        else pageOffset = 10;
                        NoticeInfo swap = new NoticeInfo();
                        for(int j=0;j<pageOffset;j++){
                            swap.title[j] = noticeInfo.title[9-j-(10-pageOffset)];
                            swap.date[j] = noticeInfo.date[9-j-(10-pageOffset)];
                            swap.body[j] = noticeInfo.body[9-j-(10-pageOffset)];
                        }
                        noticeInfo = swap;

                        pageChange.setPage(pageValue, noticeMax);
                        noticeListView.smoothScrollToPositionFromTop(0, 10, 300);
                        adapter.putInfo(noticeInfo, pageValue, pageOffset, noticeMax);
                        noticeListView.setAdapter(adapter);  //????????? ?????? ?????? ????????? ??????
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
                    noticeMax = apiCaller.receivedJSONObject.getInt("maxindex");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                noticeInfo = apiCaller.loadNotice(pageValue);
                if( noticeMax/10 == pageValue-1) pageOffset = noticeMax - (pageValue-1)*10;
                else pageOffset = 10;
                NoticeInfo swap = new NoticeInfo();
                for(int j=0;j<pageOffset;j++){
                    swap.title[j] = noticeInfo.title[9-j-(10-pageOffset)];
                    swap.date[j] = noticeInfo.date[9-j-(10-pageOffset)];
                    swap.body[j] = noticeInfo.body[9-j-(10-pageOffset)];
                }
                noticeInfo = swap;
                pageChange.setPage(pageValue, noticeMax);
                adapter.putInfo(noticeInfo, pageValue, pageOffset, noticeMax);
                noticeListView.setAdapter(adapter);  //????????? ?????? ?????? ????????? ??????
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
                    noticeMax = apiCaller.receivedJSONObject.getInt("maxindex");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                noticeInfo = apiCaller.loadNotice(pageValue);
                if( noticeMax/10 == pageValue-1) pageOffset = noticeMax - (pageValue-1)*10;
                else pageOffset = 10;
                NoticeInfo swap = new NoticeInfo();
                for(int j=0;j<pageOffset;j++){
                    swap.title[j] = noticeInfo.title[9-j-(10-pageOffset)];
                    swap.date[j] = noticeInfo.date[9-j-(10-pageOffset)];
                    swap.body[j] = noticeInfo.body[9-j-(10-pageOffset)];
                }
                noticeInfo = swap;
                pageChange.setPage(pageValue, noticeMax);
                adapter.putInfo(noticeInfo, pageValue, pageOffset, noticeMax);
                noticeListView.setAdapter(adapter);  //????????? ?????? ?????? ????????? ??????
                noticeListView.smoothScrollToPositionFromTop(0, 10, 300);
            }
        });
        noticeListView.setOnItemClickListener(itemClickListener);
        noticeExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeRelative.setVisibility(View.INVISIBLE);
                for(int i=0;i<7;i++){
                    pageBtn[i].setClickable(true);
                }
                noticeListView.setOnItemClickListener(itemClickListener);
                noticeListView.setEnabled(true);
            }
        });
        noticeExitBtn.setOnTouchListener(new OkTouch());
        goSelectAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
