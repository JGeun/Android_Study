package jgeun.study.navigationview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import jgeun.study.navigationview.databinding.ActivityFragmentDrawerBinding
import jgeun.study.navigationview.view.*

class FragmentDrawerActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFragmentDrawerBinding.inflate(layoutInflater)
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

        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 왼쪽 상단 버튼 만들기
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) //왼쪽 상단 버튼 아이콘 지정

        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, HomeFragment())
            .commitAllowingStateLoss()

        binding.navigationView.setNavigationItemSelectedListener {
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

    private fun changeFragment(idx: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, fragmentList[idx]).commitAllowingStateLoss()
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}