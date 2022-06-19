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

        binding.btnDrawerByFragment.setOnClickListener {
            startActivity(Intent(this, FragmentDrawerActivity::class.java))
        }

        binding.btnDrawerByNavigation.setOnClickListener {
            startActivity(Intent(this, NavigationDrawerActivity::class.java))
        }

        binding.btnBtmNaviFragment.setOnClickListener {
            startActivity(Intent(this, BottomNaviFragmentActivity::class.java))
        }

        binding.btnBtmNavigation.setOnClickListener {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
        }
    }
}