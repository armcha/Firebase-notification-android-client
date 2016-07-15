package com.luseen.firebasenotificationtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Chatikyan on 15.07.2016-23:22.
 */

class GenerateNotification {

    static  NotificationCompat.Builder getBaseNotificationCompatBuilder(Context context, RemoteMessage remoteMessage) {

        NotificationCompat.Style notificationStyle;
        NotificationCompat.BigPictureStyle bigPictureStyle;
        NotificationCompat.BigTextStyle bigTextStyle;

        String message = remoteMessage.getData().get("message");
        String title = remoteMessage.getData().get("title");
        String imageUrl = remoteMessage.getData().get("bigPictureUrl");

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (NotificationHelper.hasValidUrl(imageUrl)) {
            Bitmap bigPicture = NotificationHelper.getBitmapFromURL(imageUrl);
            bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(bigPicture);
            notificationStyle = bigPictureStyle;
        } else {
            bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.bigText(message);
            notificationStyle = bigTextStyle;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setStyle(notificationStyle)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentTitle(title)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_EVENT);
            builder.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }

        return builder;
    }
}
