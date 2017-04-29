package com.example.teacher.db042901;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    Context context;
    RemoteViews remoteViews;
    Notification notification;
    final int NOTIFICATION_ID = 321;
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

        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flower);
        bigPictureStyle.bigPicture(bitmap);
        bigPictureStyle.setSummaryText("花");

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("內文")
                .setContentIntent(pi)
                .setStyle(bigPictureStyle)
                .setAutoCancel(false);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
    public void click2(View v)
    {
        manager.cancel(NOTIFICATION_ID);
    }

    public void clickDownload(View v)
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setImageViewResource(R.id.imageView, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.textView2, "下載中...");
        // builder.setCustomContentView(remoteViews);
        builder.setContent(remoteViews);
        notification = builder.build();
        manager.notify(333, notification);
        DownloadThread t = new DownloadThread();
        t.start();
    }

    class DownloadThread extends Thread
    {
        int progress = 0;
        int TotalProgress = 100;
        @Override
        public void run()
        {
            for (progress=0;progress<=TotalProgress;progress++)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        remoteViews.setProgressBar(R.id.progressBar, 100, progress, false);
                        manager.notify(333, notification);
                    }
                });
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
