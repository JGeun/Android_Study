package com.jgeun.musicservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicPlayerService : Service() {

	private val mp by lazy { MediaPlayer.create(this, R.raw.music_service_test_file) }

	// 서비스가 호출되었을 때 한번만 호출
	override fun onCreate() {
		super.onCreate()
		Log.e(TAG, "onCreate()")
		mp.isLooping = false // 반복재생
	}

	// 서비스가 호출될때마다 호출 (음악재생)
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.e(TAG, "Action Received = ${intent?.action}")

		// intent가 시스템에 의해 재생생되었을 때 null값이므로 Java에서는 null check 필수
		when (intent?.action) {
			Actions.START_FOREGROUND -> {
				Log.e(TAG, "Start Foreground 인텐트를 받음")
				startMusic()
			}
			Actions.STOP_FOREGROUND -> {
				Log.e(TAG, "Stop Foreground 인텐트를 받음")
				stopMusic()
			}
			Actions.PREV -> Log.e(TAG, "Clicked = 이전")
			Actions.PLAY -> Log.e(TAG, "Clicked = 재생")
			Actions.NEXT -> Log.e(TAG, "Clicked = 다음")

		}
		return START_STICKY
	}

	private fun stopMusic() {
		stopForeground(STOP_FOREGROUND_REMOVE)
		stopSelf()
	}

	private fun startMusic() {
		val notification = MusicNotification.createNotification(this)
		startForeground(NOTIFICATION_ID, notification)
		mp.start()
	}

	override fun onBind(intent: Intent): IBinder? {
		// Service 객체와 통신할 때 사용
		return null
	}

	// 서비스가 종료될 때 음악 종료
	override fun onDestroy() {
		super.onDestroy()
		Log.e(TAG, "onDestroy()")
		mp.stop()
	}

	companion object {
		const val TAG = "[MusicPlayerService]"
		const val NOTIFICATION_ID = 20
	}
}