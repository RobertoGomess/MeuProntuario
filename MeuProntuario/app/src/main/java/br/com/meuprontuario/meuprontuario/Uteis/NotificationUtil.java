package br.com.meuprontuario.meuprontuario.Uteis;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import br.com.meuprontuario.meuprontuario.R;

/**
 * Created by Beto on 04/10/2017.
 */

public class NotificationUtil {

    private static final String TAG = "Livro Android";

    public static void create(Context context, int id, Intent intent, String contentTitle, String contextText){

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent p = PendingIntent.getActivity(context, 0 , intent ,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setAutoCancel(true)
                .setContentIntent(p)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(contentTitle)
                .setContentText(contextText);

        Notification n = builder.build();

        manager.notify(id,n);

    }



}
