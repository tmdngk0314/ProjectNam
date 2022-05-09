package com.example.projectnam;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ReserveLongActivity extends AppCompatActivity {
    Button restartBtn1, restartBtn2, chooseDayBtn;
    ImageButton nextBtn,imgBtnLogout;
    CalendarView calendar;
    RelativeLayout relativeCalendar;
    TextView text1;
    int year=0,month=0,day=0,year2=0,month2=0,day2=0;
    String location,lockername;
    long tim, now = System.currentTimeMillis();  //

    CalendarDate calendarDate = new CalendarDate();
    int setTextLoca=0;
    Boolean isStartSelected=false;
    Boolean isEndSelected=false;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReserveLongActivity.this, ReserveActivity.class);
        intent.putExtra("location", location);
        intent.putExtra("lockername", lockername);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_long);

        restartBtn1 = (Button) findViewById(R.id.restartbtn1);
        restartBtn2 = (Button) findViewById(R.id.restartbtn2);
        chooseDayBtn = (Button)findViewById(R.id.chooseDayBtn);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        imgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);
        calendar = (CalendarView) findViewById(R.id.calendar);
        relativeCalendar = (RelativeLayout)findViewById(R.id.relativeCalendar);
        text1 = (TextView)findViewById(R.id.text1);
        Intent is = getIntent();
        location=is.getStringExtra("location");
        lockername=is.getStringExtra("lockername");

        calendarDate.setCalendar(calendar);

        restartBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextLoca=2;
                relativeCalendar.setVisibility(VISIBLE);
                calendar.setVisibility(VISIBLE);
                text1.setVisibility(INVISIBLE);
                calendarDate.setOffset(setTextLoca);
                calendarDate.setMax();
                calendarDate.setMin();
                isStartSelected=true;
            }
        });
        restartBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextLoca=1;
                relativeCalendar.setVisibility(VISIBLE);
                calendar.setVisibility(VISIBLE);
                text1.setVisibility(INVISIBLE);
                calendarDate.setOffset(setTextLoca);
                calendarDate.setMax();
                calendarDate.setMin();
                isEndSelected=true;
            }
        });

        chooseDayBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                relativeCalendar.setVisibility(INVISIBLE);
                calendar.setVisibility(GONE);
                text1.setVisibility(VISIBLE);
                if(setTextLoca==1){
                    restartBtn1.setText(calendarDate.Year+"년" + calendarDate.Month+ "월" + calendarDate.Day + "일");
                   year = calendarDate.Year;
                   month = calendarDate.Month;
                   day = calendarDate.Day;
                    calendarDate.saveStart();
                }
                else {
                    calendarDate.saveFinish();
                    restartBtn2.setText(calendarDate.Year+"년" + calendarDate.Month+ "월" + calendarDate.Day + "일");
                    year2 = calendarDate.Year;
                    month2 = calendarDate.Month;
                    day2 = calendarDate.Day;

                }
            }
        });
        calendar.setOnDateChangeListener(calendarDate);
        chooseDayBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        chooseDayBtn.setTextColor(R.drawable.choose_reserve_day_push);
                        return false;
                    case MotionEvent.ACTION_UP:
                        chooseDayBtn.setTextColor(R.drawable.choose_reserve_day);
                        return false;
                    default: return false;
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(isStartSelected && isEndSelected)){
                    Toast.makeText(ReserveLongActivity.this, "날짜를 모두 선택하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String startdate=year+"-"+month+"-"+day;
                String enddate=year2+"-"+month2+"-"+day2;
                CallRestApi apiCaller=new CallRestApi();
                String result=apiCaller.reserve(lockername, startdate, enddate);
                if(result.equals("success")) {
                    Intent intent = new Intent(ReserveLongActivity.this, ReserveLongResultActivity.class);
                    intent.putExtra("연", year);
                    intent.putExtra("월", month);
                    intent.putExtra("일", day);
                    intent.putExtra("연2", year2);
                    intent.putExtra("월2", month2);
                    intent.putExtra("일2", day2);
                    intent.putExtra("lockername", lockername);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }else if(result.equals("diffIP")){
                    Log.e("Login Session", "다른 기기에서 로그인되었음" );
                    Toast.makeText(ReserveLongActivity.this, "다른 기기에서 로그인되어 종료합니다.", Toast.LENGTH_SHORT).show();
                    apiCaller.logout();
                    ActivityCompat.finishAffinity(ReserveLongActivity.this);
                    System.exit(0);
                }else if(result.equals("not idle")){
                    Log.e("not idle:", "not idle");
                    Toast.makeText(ReserveLongActivity.this, "사물함 이용 중엔 예약할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else if(result.equals("overdue")){
                    Toast.makeText(ReserveLongActivity.this, "연체 중엔 예약할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else if(result.equals("full")){
                    Toast.makeText(ReserveLongActivity.this, "해당 날짜에 예약이 가득 찼습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ReserveLongActivity.this, "unknown statement", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveLongActivity.this, ReserveActivity.class);
                intent.putExtra("location", location);
                intent.putExtra("lockername", lockername);
                startActivity(intent);
                finish();
            }
        });

        nextBtn.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        nextBtn.setBackgroundResource(R.drawable.touchreserve);
                        return false;
                    case MotionEvent.ACTION_UP:
                        nextBtn.setBackgroundResource(R.drawable.reservebtn);
                        return false;
                    default: return false;
                }

            }
        });

    }
}