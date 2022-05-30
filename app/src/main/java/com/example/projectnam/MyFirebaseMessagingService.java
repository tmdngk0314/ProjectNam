package com.example.projectnam;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        Log.e("NEW_TOKEN", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> params = remoteMessage.getData();
        JSONObject object = new JSONObject(params);
        Log.e("JSON_OBJECT", object.toString());

        //String NOTIFICATION_CHANNEL_ID = "Nilesh_channel";


        long pattern[] = {0, 1000};

        String title="";
        String body="";
        String category="";
        try {
            title = object.getString("title");
            body = object.getString("body");
            category = object.getString("category");
        }catch(Exception e){
            e.printStackTrace();
        }
        String NOTIFICATION_CHANNEL_ID;
        NotificationChannel notificationChannel;
        switch(category){
            case "client_overdue":
                NOTIFICATION_CHANNEL_ID="client_overdue";
                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "연체 알림",
                        NotificationManager.IMPORTANCE_HIGH);
                break;
            case "notice":
                NOTIFICATION_CHANNEL_ID="notice";
                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "공지사항",
                        NotificationManager.IMPORTANCE_HIGH);
                break;
            case "etc":
                NOTIFICATION_CHANNEL_ID="etc";
                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "기타 알림",
                        NotificationManager.IMPORTANCE_HIGH);
                break;
            default:
                NOTIFICATION_CHANNEL_ID="";
                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "",
                        NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            notificationChannel.setDescription("");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(pattern);
            notificationChannel.enableVibration(true);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        // to diaplay notification in DND Mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
            channel.canBypassDnd();
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        PendingIntent mPendingIntent;
        switch(category){
            case "client_overdue":
                if(!CurrentLoggedInID.isLoggedIn) {
                    mPendingIntent = PendingIntent.getActivity( // 알림 클릭 시 액티비티 이동
                            this,
                            0, // 보통 default값 0을 삽입
                            new Intent(getApplicationContext(), MainActivity.class),
                            PendingIntent.FLAG_IMMUTABLE
                    );
                    CurrentLoggedInID.reservePush="client_overdue";
                }else{
                    CallRestApi apiCaller = new CallRestApi();
                    ReservationStatus status;
                    status=apiCaller.checkReservationStatus();
                    Intent intent = new Intent(getApplicationContext(), LockerListActivity.class);
                    Intent intent2 = new Intent(getApplicationContext(),ReserveStateActivity.class);
                    Log.e("test", status.result);
                    if(status.result.equals("idle")) {
                        mPendingIntent = PendingIntent.getActivity( // 알림 클릭 시 액티비티 이동
                                this,
                                0, // 보통 default값 0을 삽입
                                intent,
                                PendingIntent.FLAG_IMMUTABLE
                        );
                    }
                    else if(status.result.equals("reserved") || status.result.equals("using") || status.result.equals("overdue")){
                        intent2.putExtra("result", status.result);
                        intent2.putExtra("startdate", status.startdate);
                        intent2.putExtra("enddate", status.enddate);
                        intent2.putExtra("usinglockername", status.usinglockername);
                        intent2.putExtra("location", status.location);
                        if(status.result.equals("using"))
                            intent2.putExtra("lockernum", status.lockernum);
                        mPendingIntent = PendingIntent.getActivity( // 알림 클릭 시 액티비티 이동
                                this,
                                0, // 보통 default값 0을 삽입
                                intent2,
                                PendingIntent.FLAG_IMMUTABLE
                        );
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "unkown statement", Toast.LENGTH_SHORT).show();
                        mPendingIntent = null;
                    }

                }
                break;
            case "notice":
                if(!CurrentLoggedInID.isLoggedIn) {
                    mPendingIntent = PendingIntent.getActivity( // 알림 클릭 시 액티비티 이동
                            this,
                            0, // 보통 default값 0을 삽입
                            new Intent(getApplicationContext(), MainActivity.class),
                            PendingIntent.FLAG_IMMUTABLE
                    );
                    CurrentLoggedInID.reservePush="notice";
                }else{
                    Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                    mPendingIntent = PendingIntent.getActivity( // 알림 클릭 시 액티비티 이동
                            this,
                            0, // 보통 default값 0을 삽입
                            intent,
                            PendingIntent.FLAG_IMMUTABLE
                    );
                }
                break;
            default:
                if(!CurrentLoggedInID.isLoggedIn) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    mPendingIntent = PendingIntent.getActivity( // 알림 클릭 시 액티비티 이동
                            this,
                            0, // 보통 default값 0을 삽입
                            intent,
                            PendingIntent.FLAG_IMMUTABLE
                    );
                }else
                    mPendingIntent=null;

        }

        notificationBuilder.setAutoCancel(true)
                .setColor(ContextCompat.getColor(this, R.color.black))
                .setContentTitle(title)
                .setContentText(body)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.app_icon)
                .setAutoCancel(true);
        if(mPendingIntent!=null){
            notificationBuilder.setContentIntent(mPendingIntent);
        }



        mNotificationManager.notify(1000, notificationBuilder.build());
    }
}
