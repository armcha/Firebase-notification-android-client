package com.luseen.firebasenotificationtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

/**
 * Created by Chatikyan on 15.07.2016-19:28.
 */

class NotificationHelper {

    static Bitmap getBitmapFromURL(String location) {
        try {
            return BitmapFactory.decodeStream(new URL(location).openConnection().getInputStream());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    static boolean hasValidUrl(String url) {
        return url != null && !url.isEmpty() && url.startsWith("http://") || url.startsWith("https://");
    }

}
