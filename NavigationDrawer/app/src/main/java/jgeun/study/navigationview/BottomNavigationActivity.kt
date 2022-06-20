package jgeun.study.navigationview

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import jgeun.study.navigationview.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    // Configuration options for NavigationUI methods that interact with implementations of the app bar pattern
    // app bar 패턴의 구현과 상호작용하는 NavigationUI 메소드를 위한 구성 옵션
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val binding by lazy {
        ActivityBottomNavigationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) //툴바를 action bar로 설정

        // nav에 따라 변경될 fragment 및 그에 따른 controller 지정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController) //bottomNav에 navController 연결
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