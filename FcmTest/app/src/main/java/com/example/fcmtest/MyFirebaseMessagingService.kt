package com.example.fcmtest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage

enum class NotificationType(val title: String, val id: Int) {
    NORMAL("일반 알림", 0),
    EXPANDABLE("확장형 알림", 1),
    CUSTOM("커스텀 알림", 3),
}

class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        const val TAG = "MessageService"
        private const val CHANNER_NAME = "Push Notification"
        private const val CHANNEL_DESCRIPTION = "Push Notification을 위한 채널"
        private const val CHANNEL_ID = "Channel_id"
    }

    /* 토큰 생성 메서드*/
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    /* 메세지 수신 메서드*/
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "onMessageReceived() - remoteMessage : ${message}")
        Log.d(TAG, "onMessageReceived() - from : ${message.from}")
        Log.d(TAG, "onMessageReceived() - notification : ${message.notification?.body}")

        val type = message.data["type"]?.let { NotificationType.valueOf(it) } ?: kotlin.run {
            NotificationType.NORMAL
        }
        val title = message.data["title"]
        val message = message.data["message"]

        Log.d(TAG, "onMessageReceived() - type : $type")
        Log.d(TAG, "onMessageReceived() - title : $title")
        Log.d(TAG, "onMessageReceived() - message : $message")

        sendNotification(type, title, message)
    }

    /* 알림 생성 메서드 */
    private fun sendNotification(
        type: NotificationType,
        title: String?,
        message: String?
    ) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Oreo(26) 이상 버전에는 channel 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNER_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }

        //알림이 여러개 표시되도록 requestCode 를 추가
        NotificationManagerCompat.from(this)
            .notify((System.currentTimeMillis()/100).toInt(), createNotification(type, title, message))

    }

    private fun createNotification(
        type: NotificationType,
        title: String?,
        message: String?): Notification {

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", " ${type.title} 타입 ")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this,
            (System.currentTimeMillis()/100).toInt(), intent, FLAG_UPDATE_CURRENT) //알림이 여러개 표시되도록 requestCode 를 추가

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent) //알림 눌렀을 때 실행할 Intent 설정
            .setAutoCancel(true)

        when (type) {
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE -> {
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("$message \n 가나다라마바사")
                )
            }
            NotificationType.CUSTOM -> {
                notificationBuilder.setStyle(
                    NotificationCompat.DecoratedCustomViewStyle()
                ).setCustomContentView(
                    RemoteViews(
                        packageName,
                        R.layout.view_custom_notification
                    ).apply {
                        setTextViewText(R.id.tv_custom_title, title)
                        setTextViewText(R.id.tv_custom_message, message)
                    }
                )
            }
        }
        return notificationBuilder.build()
    }
}