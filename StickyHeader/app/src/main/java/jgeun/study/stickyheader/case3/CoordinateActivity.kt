package jgeun.study.stickyheader.case3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.stickyheader.BannerAdapter
import jgeun.study.stickyheader.ContentsVpAdapter
import jgeun.study.stickyheader.databinding.ActivityCoordinateBinding

class CoordinateActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityCoordinateBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		with(binding.banner) {
			adapter = BannerAdapter(this@CoordinateActivity)
		}

		with(binding.categoryVp) {
			adapter = ContentsVpAdapter(this@CoordinateActivity)
		}

		TabLayoutMediator(binding.categoryTab, binding.categoryVp) { tab, position ->
			tab.text = "tab$position"
		}.attach()
	}
}