package jgeun.study.stickyheader.case1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.stickyheader.BannerAdapter
import jgeun.study.stickyheader.ContentsVpAdapter
import jgeun.study.stickyheader.databinding.ActivityOpenSourceBinding

class OpenSourceActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityOpenSourceBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		with(binding.banner) {
			adapter = BannerAdapter(this@OpenSourceActivity)
		}

		with(binding.categoryVp) {
			adapter = ContentsVpAdapter(this@OpenSourceActivity)
		}

		TabLayoutMediator(binding.categoryTab, binding.categoryVp) { tab, position ->
			tab.text = "tab$position"
		}.attach()

	}
}