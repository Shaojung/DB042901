package com.example.teacher.db042901;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void click1(View v)
    {
        Notification.Builder builder = new Notification.Builder(context);
        Intent it = new Intent(MainActivity.this, DetailActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 2, it, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("內文")
                .setContentIntent(pi)
                .setAutoCancel(true);
        manager.notify(1, builder.build());


    }
}
