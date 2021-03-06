package jgeun.study.navigationview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jgeun.study.navigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Fragment를 이용한 Drawer구현
        binding.btnDrawerByFragment.setOnClickListener {
            startActivity(Intent(this, FragmentDrawerActivity::class.java))
        }

        // Navigation을 이용한 Drawer구현
        binding.btnDrawerByNavigation.setOnClickListener {
            startActivity(Intent(this, NavigationDrawerActivity::class.java))
        }

        // Fragment를 이용한 Bottom tab 구현
        binding.btnBtmNaviFragment.setOnClickListener {
            startActivity(Intent(this, BottomTabFragmentActivity::class.java))
        }

        // Bottom Navigation 구현
        binding.btnBtmNavigation.setOnClickListener {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
        }
    }
}