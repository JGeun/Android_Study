package com.soma.swm_mvp_first

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class TestAlarmReceiver2 : BroadcastReceiver(){

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "1000"
        val NOTIFICATION_ID = 100
        val NOTIFICATION_REQUEST_CODE = 1000
    }

    override fun onReceive(context: Context, intent: Intent?) {

        val intent = Intent(context, Test2DestinationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK;

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Alarm Manager")
            .setContentText("Alaram Manager Content Text")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
    }
}