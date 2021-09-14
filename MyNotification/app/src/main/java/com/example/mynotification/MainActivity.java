package com.example.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
/*NotificationManager manager;
NotificationCompat.Builder builder;
PendingIntent pi;
Intent i;*/
    OneTimeWorkRequest oneTimeWorkRequest;
    PeriodicWorkRequest PeriodicWorkRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oneTimeWorkRequest=new OneTimeWorkRequest.Builder(SigleTimeWorker.class).build();
        PeriodicWorkRequest=new PeriodicWorkRequest.Builder(PeriodicWorkRequest.class,
                15, TimeUnit.MINUTES).build();
        /*manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        i=new Intent(this,MainActivity.class);
        pi=PendingIntent.getActivity(this,123,i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        myNotificationChannel();*/
    }

    /*private void myNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel cn=new NotificationChannel("MY CHANNEL",
                    "SRM",NotificationManager.IMPORTANCE_HIGH);
            cn.enableLights(true);
            cn.enableVibration(true);
            cn.setLightColor(Color.GREEN);
            manager.createNotificationChannel(cn);
        }
    }*/

    public void showNotification(View view) {
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
        WorkManager.getInstance(this).enqueue(PeriodicWorkRequest);
        /*builder=new NotificationCompat.Builder(this,"MY CHANNEL");
        builder.setContentTitle("My own Notification");
        builder.setContentText("This is my notification from internship");
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setContentIntent(pi);
        builder.addAction(R.drawable.ic_reply,"Reply",pi);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.cap);
        builder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap));
        manager.notify(1235,builder.build());*/
    }

    public void stopMyMusic(View view) {
        Intent myintent=new Intent(this,MyMusicService.class);
        stopService(myintent);
    }

    public void startMyMusic(View view) {
        Intent myintent=new Intent(this,MyMusicService.class);
        startService(myintent);
    }
}