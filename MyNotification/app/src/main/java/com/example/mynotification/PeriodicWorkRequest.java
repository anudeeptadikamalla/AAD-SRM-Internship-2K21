package com.example.mynotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class PeriodicWorkRequest extends Worker {
    NotificationManager manager;
    NotificationCompat.Builder builder;
    PendingIntent pi;
    Intent i;

    public PeriodicWorkRequest(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Result doWork() {
        manager= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        i=new Intent(getApplicationContext(),MainActivity.class);
        pi=PendingIntent.getActivity(getApplicationContext(),123,i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        myNotificationChannel();
        showNotification();
        return null;
    }
    public void showNotification() {
        builder=new NotificationCompat.Builder(getApplicationContext(),"MY CHANNEL");
        builder.setContentTitle("My own Notification");
        builder.setContentText("This is my notification from internship");
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setContentIntent(pi);
        builder.addAction(R.drawable.ic_reply,"Reply",pi);
        Bitmap bitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.cap);
        builder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap));
        manager.notify(1235,builder.build());
    }
    private void myNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel cn=new NotificationChannel("MY CHANNEL",
                    "SRM",NotificationManager.IMPORTANCE_HIGH);
            cn.enableLights(true);
            cn.enableVibration(true);
            cn.setLightColor(Color.GREEN);
            manager.createNotificationChannel(cn);
        }
    }
}
