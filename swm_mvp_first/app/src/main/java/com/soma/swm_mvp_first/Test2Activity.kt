package com.soma.swm_mvp_first

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.soma.swm_mvp_first.databinding.ActivityTest2Binding
import java.util.*

enum class NotificationType(val title: String, val id: Int) {
    NORMAL("일반 알림", 0),
    EXPANDABLE("확장형 알림", 1),
    CUSTOM("커스텀 알림", 3),
}

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MessageService"
        private const val CHANNER_NAME = "Push Notification"
        private const val CHANNEL_DESCRIPTION = "Push Notification을 위한 채널"
        private const val CHANNEL_ID = "Channel_id"
        private const val NOTIFICATION_CHANNEL_ID = "1000"
    }

    private val binding by lazy {
        ActivityTest2Binding.inflate(layoutInflater)
    }

    private lateinit var picker : MaterialTimePicker
    private lateinit var calendar : Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createNotificationChannel()

        binding.selectTimeBtn.setOnClickListener {

            showTimePicker()
        }

        binding.setAlarmBtn.setOnClickListener {
            setAlarm()
        }

        binding.cancelAlarmBtn.setOnClickListener {
            cancelAlarm()
        }
    }

    private fun cancelAlarm() {

        val intent = Intent(this, TestAlarmReceiver2::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        if(alarmManager == null) {
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        }

        alarmManager.cancel(pendingIntent)
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show()

    }

    private fun setAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, TestAlarmReceiver2::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent)

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build();

        picker.show(supportFragmentManager, "android")

        picker.addOnPositiveButtonClickListener {
            if (picker.hour > 12) {
                binding.selectedTime.setText(
                    String.format("%02d", "${picker.hour-12} : ${String.format("%02d", picker.minute)} PM")
                )
            } else {
                binding.selectedTime.text = "${picker.hour} : ${picker.minute} AM"
            }

            calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, picker.hour)
            calendar.set(Calendar.MINUTE, picker.minute)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
        }
    }

    /*private fun sendNotification(
        type: NotificationType,
        title: String?,
        message: String?) {

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Oreo(26) 이상 버전에는 channel 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel (
                NOTIFICATION_CHANNEL_ID,
                CHANNER_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }

        // 알림이 여러개 표시되도록 requestCode 를 추가
        NotificationManagerCompat.from(this)
            .notify((System.currentTimeMillis()/100).toInt(), createNotification(type, title, message))
    }

    private fun createNotification(
        type: NotificationType,
        title: String?,
        message: String?) : Notification{

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", " ${type.title} 타입 ")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(this,
            (System.currentTimeMillis()/100).toInt(), intent, FLAG_UPDATE_CURRENT)

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
        }
        return notificationBuilder.build()
    }*/

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Oreo(26) 이상 버전에는 channel 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel (
                NOTIFICATION_CHANNEL_ID,
                CHANNER_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }
/*
        // 알림이 여러개 표시되도록 requestCode 를 추가
        NotificationManagerCompat.from(this)
            .notify((System.currentTimeMillis()/100).toInt(), createNotification(type, title, message))*/
    }
}