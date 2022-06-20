package jgeun.study.navigationview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import jgeun.study.navigationview.databinding.ActivityBottomTabFragmentBinding
import jgeun.study.navigationview.view.*

class BottomTabFragmentActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBottomTabFragmentBinding.inflate(layoutInflater)
    }

    private val fragmentList = arrayListOf<Fragment>(
        HomeFragment(),
        ImportFragment(),
        GalleryFragment(),
        SlideShowFragment(),
        ToolsFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) //툴바를 action bar로 설정

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 왼쪽 상단 버튼 만들기
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) //왼쪽 상단 버튼 아이콘 지정

        //HomeFragment 첫화면으로 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, HomeFragment())
            .commitAllowingStateLoss()


        // 첫 화면 homeFragment로 지정
        binding.bottomNav.selectedItemId = R.id.homeFragment

        // Item 선택에 따른 화면 전환
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    changeFragment(0)
                }
                R.id.importFragment -> {
                    changeFragment(1)
                }
                R.id.galleryFragment -> {
                    changeFragment(2)
                }
                R.id.slideShowFragment -> {
                    changeFragment(3)
                }
                R.id.toolsFragment -> {
                    changeFragment(4)
                }
            }
            true
        }
    }

    // fragment 교체
    private fun changeFragment(idx: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, fragmentList[idx]).commitAllowingStateLoss()
    }
}