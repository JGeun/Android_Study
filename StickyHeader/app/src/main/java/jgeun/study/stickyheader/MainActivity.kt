package jgeun.study.stickyheader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jgeun.study.stickyheader.case1.OpenSourceActivity
import jgeun.study.stickyheader.case2.RecyclerViewActivity
import jgeun.study.stickyheader.case3.CoordinateActivity
import jgeun.study.stickyheader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.btnOpensource.setOnClickListener {
			startActivity(Intent(this, OpenSourceActivity::class.java))
		}

		binding.btnMultiViewtypeRecyclerview.setOnClickListener {
			startActivity(Intent(this, RecyclerViewActivity::class.java))
		}

		binding.btnAppbarlayout.setOnClickListener {
			startActivity(Intent(this, CoordinateActivity::class.java))
		}
	}
}