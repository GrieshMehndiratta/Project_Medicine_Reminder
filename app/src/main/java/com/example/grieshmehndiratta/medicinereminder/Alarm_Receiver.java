package com.example.grieshmehndiratta.medicinereminder;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class Alarm_Receiver extends BroadcastReceiver {
    private final  int N_id=23;
    Medicine_Reminder medicine_reminder;
    @Override
    public void onReceive(Context context, Intent intent) {

        createTip(context);

    }

    public void createTip(Context context) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);

        medicine_reminder=new Medicine_Reminder();

        builder.setSmallIcon(R.drawable.image2);
        builder.setContentTitle( "This is my App" );
        builder.setAutoCancel( true );
        builder.setContentText( "Please Take Ur Medicine  "+ medicine_reminder.Med_Name() );
        builder.setCategory( Notification.CATEGORY_REMINDER );
        builder.setDefaults( Notification.DEFAULT_SOUND );

        Notification notification=builder.build();
        NotificationManagerCompat.from( context ).notify( N_id,notification );
    }
}
