package com.example.prajjwal_ubuntu.appomanage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by prajjwal-ubuntu on 22/9/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent =new Intent (this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notifiactionBuilder =new NotificationCompat.Builder(this);
        notifiactionBuilder.setContentTitle(" Notification");
        notifiactionBuilder.setContentText(remoteMessage.getNotification().getBody());
        notifiactionBuilder.setAutoCancel(true);
        notifiactionBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notifiactionBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notifiactionBuilder.build());
    }
}
