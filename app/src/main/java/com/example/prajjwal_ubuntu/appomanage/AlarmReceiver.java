package com.example.prajjwal_ubuntu.appomanage;

/**
 * Created by prajjwal-ubuntu on 28/9/18.
 */

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;


/**
 * Created by prajjwal-ubuntu on 27/9/18.
 */

public class AlarmReceiver extends BroadcastReceiver {

    NotificationCompat.Builder noti;
    private static final int id = 45612;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "sadasdd", LENGTH_LONG).show();
        Log.d("res", "dfsfs");
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.start();
//        noti = new NotificationCompat.Builder(context);
//        noti.setAutoCancel(true);
//
//        noti.setWhen(System.currentTimeMillis());
//        noti.setContentTitle("scsad");
//
////        Intent intent1 = new Intent(context, MainActivity.class);
////        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
////        noti.setContentIntent(pendingIntent);
//
//        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(id, noti.build());

        NotificationCompat.Builder noti = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.stat_notify_error)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                .setContentTitle("Appointment Manager")
                .setContentText("You have an Appointment in next 15 mins");
        noti.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(1, noti.build());
    }
}