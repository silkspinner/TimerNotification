package com.nimsdev.timernotification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button alertButton, alertButton2, alertButton3, alertButton4;
    static String alertText = "Five Second Alert!";
    static int alertDelay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        alertButton = (Button)findViewById(R.id.alertButton);
        alertButton2 = (Button)findViewById(R.id.alertButton2);
        alertButton3 = (Button)findViewById(R.id.alertButton3);
        alertButton4 = (Button)findViewById(R.id.alertButton4);

        alertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set 5 sec alert
                alertText = "Five Second Alert!";
                alertDelay = 5000;

                setContentView(R.layout.activity_main);
                scheduleNotification(getNotification(alertText), alertDelay);
            }
        });

        alertButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set 5 sec alert
                alertText = "Ten Second Alert!";
                alertDelay = 10000;

                setContentView(R.layout.activity_main);
                scheduleNotification(getNotification(alertText), alertDelay);
            }
        });

        alertButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set 5 sec alert
                alertText = "Thirty Second Alert!";
                alertDelay = 30000;

                setContentView(R.layout.activity_main);
                scheduleNotification(getNotification(alertText), alertDelay);
            }
        });

        alertButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set 5 sec alert
                alertText = "Sixty Second Alert!";
                alertDelay = 60000;

                setContentView(R.layout.activity_main);
                scheduleNotification(getNotification(alertText), alertDelay);
            }
        });

    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(content);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }

}