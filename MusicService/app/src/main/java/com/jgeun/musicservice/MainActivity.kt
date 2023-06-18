package com.jgeun.musicservice

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.jgeun.musicservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	companion object {
		const val DENIED = "denied"
		const val EXPLAINED = "explained"
	}

	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val registerForActivityResult = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
		val deniedPermissionList = permissions.filter { !it.value }.map { it.key }
		when {
			deniedPermissionList.isNotEmpty() -> {
				val map = deniedPermissionList.groupBy { permission ->
					if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
				}
				map[DENIED]?.let {
					// 단순히 권한이 거부 되었을 때
				}
				map[EXPLAINED]?.let {
					// 권한 요청이 완전히 막혔을 때(주로 앱 상세 창 열기)
				}
			}
			else -> {
				// 모든 권한이 허가 되었을 때
			}
		}
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			registerForActivityResult.launch(
				arrayOf(Manifest.permission.POST_NOTIFICATIONS)
			)
		}

		binding.btnStartMusic.setOnClickListener {
			val intent = Intent(this@MainActivity, MusicPlayerService::class.java).apply {
				action = Actions.START_FOREGROUND
			}
			startService(intent) // 서비스 시작
		}

		binding.btnStopMusic.setOnClickListener {
			val intent = Intent(this@MainActivity, MusicPlayerService::class.java).apply {
				action = Actions.STOP_FOREGROUND
			}
			stopService(intent) // 서비스 종료
		}
	}
}