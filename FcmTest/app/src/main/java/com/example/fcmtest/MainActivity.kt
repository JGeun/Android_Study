package com.example.fcmtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private val tvResult: TextView by lazy {
        findViewById(R.id.tv_result)
    }

    private val tvToken: TextView by lazy {
        findViewById(R.id.tv_token)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
        updateResult()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        updateResult(true)
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                tvToken.text = task.result
            }
        }
    }

    private fun updateResult(isNetIntent: Boolean = false) {
        //true -> Notification으로 갱신된 것
        //false -> 아이콘 클릭으로 앱이 실행된 것
        tvResult.text = (intent.getStringExtra("notificationType") ?: "앱 런처") + if (isNetIntent) {
            "(으)로 갱신했습니다."
        } else {
            "(으)로 실행했습니다."
        }
    }
}