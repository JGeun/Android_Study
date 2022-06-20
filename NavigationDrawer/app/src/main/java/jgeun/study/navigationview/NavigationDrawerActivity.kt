package jgeun.study.navigationview

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import jgeun.study.navigationview.databinding.ActivityNavigationDrawerBinding

/*
* Navigation을 이용한 Drawer구현
*/
class NavigationDrawerActivity : AppCompatActivity() {

    // Configuration options for NavigationUI methods that interact with implementations of the app bar pattern
    // app bar 패턴의 구현과 상호작용하는 NavigationUI 메소드를 위한 구성 옵션
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val binding by lazy {
        ActivityNavigationDrawerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) //툴바를 action bar로 설정

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 왼쪽 상단 버튼 만들기
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) //왼쪽 상단 버튼 아이콘 지정

        // nav에 따라 변경될 fragment 및 그에 따른 controller 지정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration) //appBar에 navController 연결
        binding.navView.setupWithNavController(navController) //nav view에 menu들 연결
    }

    // Handles the Up button by delegating its behavior to the given [NavController]
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    // option이 선택됨에 따라 navigation에 의해 fragment 변경
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}