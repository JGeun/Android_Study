package jgeun.study.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.viewpager.adapter.ScreenSlidePager2Adapter
import jgeun.study.viewpager.adapter.ScreenSlidePagerAdapter
import jgeun.study.viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private final val FRAGMENT_NUMS = 5 //viewpager 수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*
        * indicator의 경우 외부 라이브러리 사용 - https://github.com/ongakuer/CircleIndicator / LICENSE: Apache 2.0
        * viewpager - indicator, viewpager2 - indicator3
        */


        /*
        * viewpager
        */
        binding.viewpager.apply {
            adapter = ScreenSlidePagerAdapter(supportFragmentManager, FRAGMENT_NUMS)
        }
        binding.tabLayout.setupWithViewPager(binding.viewpager) //tablayout 연결
        binding.indicator.setViewPager(binding.viewpager) //indicator 연결
        // viewpager 변경에 따른 indicator 변경
        binding.viewpager.adapter!!.registerDataSetObserver(binding.indicator.dataSetObserver)

        /*
        * viewpager2
        * viewpager와의 차이점
        * 1. tablayout 연결을 Activity에서 진행
        * 2. 원하는 방향(수직, 수평)으로 스크롤 가능
        */
        binding.viewpager2.apply {
            adapter = ScreenSlidePager2Adapter(this@MainActivity, FRAGMENT_NUMS)
            orientation = ViewPager2.ORIENTATION_VERTICAL
        }

        //tablayout과 viewpager2 연결
        TabLayoutMediator(binding.tabLayout2, binding.viewpager2) { tab, position ->
            tab.text = "TAB ${(position+1)}"
        }.attach()


        binding.indicator2.setViewPager(binding.viewpager2) //indicator를 viewpager와 연결

        // viewpager 변경에 따른 indicator 변경
        binding.viewpager2.adapter!!.registerAdapterDataObserver(binding.indicator2.adapterDataObserver)
    }
}