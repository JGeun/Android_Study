package com.jgeun.musicservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

object MusicNotification {
	const val CHANNEL_ID = "foreground_service_channel" // 임의의 채널 ID
	const val CONTENT_TITLE = "Music Player"
	const val CONTENT_TEXT = "My Music"
	const val SERVICE_CHANNEL_NAME = "Music Player Channel"

	fun createNotification(
		context: Context
	): Notification {

		Log.e("[MusicPlayerService]", "Create Notification")

		return makeNotification(context)
	}

	private fun makeNotification(context: Context): Notification {
		val pendingIntent = makeClassPendingIntent(context, MainActivity::class.java, Actions.MAIN)
		val prevPendingIntent = makeServicePendingIntent(context, MusicPlayerService::class.java, Actions.PREV)
		val playPendingIntent = makeServicePendingIntent(context, MusicPlayerService::class.java, Actions.PLAY)
		val nextPendingIntent = makeServicePendingIntent(context, MusicPlayerService::class.java, Actions.NEXT)

		val notification = NotificationCompat.Builder(context, CHANNEL_ID)
			.setContentTitle(CONTENT_TITLE)
			.setContentText(CONTENT_TEXT)
			.setSmallIcon(R.drawable.ic_launcher_background)
			.setOngoing(true) // true 일경우 알림 리스트에서 클릭하거나 좌우로 드래그해도 사라지지 않음
			.addAction(NotificationCompat.Action(android.R.drawable.ic_media_previous,
				"Prev", prevPendingIntent))
			.addAction(NotificationCompat.Action(android.R.drawable.ic_media_play,
				"Play", playPendingIntent))
			.addAction(NotificationCompat.Action(android.R.drawable.ic_media_next,
				"Next", nextPendingIntent))
			.setContentIntent(pendingIntent)
			.build()

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val serviceChannel = NotificationChannel(
				CHANNEL_ID,
				SERVICE_CHANNEL_NAME,
				NotificationManager.IMPORTANCE_DEFAULT
			)
			val manager = context.getSystemService(NotificationManager::class.java)
			manager?.createNotificationChannel(serviceChannel)
		}

		Log.e("[MusicPlayerService]", "make Notification: $notification")


		return notification
	}

	private fun makeClassPendingIntent(context: Context, className: Class<*>, actionType: String): PendingIntent? {

		// 알림 클릭 시 해당 클래스로 이동됨
		val notificationIntent = Intent(context, className).apply {
			action = actionType
			flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
		}

		return PendingIntent.getActivity(
			context,
			0,
			notificationIntent,
			FLAG_IMMUTABLE
		)
	}

	private fun makeServicePendingIntent(context: Context, className: Class<*>, actionType: String): PendingIntent? {
		// 각 버튼들에 관한 Intent
		val intent = Intent(context, className).apply {
			action = actionType
		}

		return PendingIntent.getService(
			context,
			0,
			intent,
			FLAG_IMMUTABLE
		)
	}
}