package jgeun.study.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jgeun.study.eventbus.databinding.ActivityMainBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.first_fragment, FirstFragment())
            .commit()

        supportFragmentManager.beginTransaction().replace(R.id.second_fragment, SecondFragment())
            .commit()
    }
}