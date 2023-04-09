package jgeun.study.stickyheader.case2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.stickyheader.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityRecyclerViewBinding.inflate(layoutInflater)
	}

	private lateinit var stickyHeaderRvAdapter: StickyHeaderRvAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		stickyHeaderRvAdapter = StickyHeaderRvAdapter(this@RecyclerViewActivity)
		with(binding.recyclerview) {
			setHasFixedSize(true)
			adapter = stickyHeaderRvAdapter
			layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
			addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
		}
	}

	private fun getSectionCallback(): StickyHeaderItemDecoration.SectionCallback {
		return object : StickyHeaderItemDecoration.SectionCallback {
			override fun isHeader(position: Int): Boolean {
				return stickyHeaderRvAdapter.isHeader(position)
			}

			override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
				return stickyHeaderRvAdapter.getHeaderView(list, position)
			}
		}
	}
}