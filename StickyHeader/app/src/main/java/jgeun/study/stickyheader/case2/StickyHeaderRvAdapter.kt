package jgeun.study.stickyheader.case2

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.stickyheader.BannerAdapter
import jgeun.study.stickyheader.ContentsVpAdapter
import jgeun.study.stickyheader.R
import jgeun.study.stickyheader.databinding.FragmentBottomBinding
import jgeun.study.stickyheader.databinding.FragmentHeaderBinding
import jgeun.study.stickyheader.databinding.FragmentTopBinding

data class AdapterItem(val type: Int, val isHeader: Boolean)

class StickyHeaderRvAdapter(
	private val activity: Activity,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var tabLayout: TabLayout? = null
	private var viewPager: ViewPager2? = null
	private var pageIdx: Int = 0

	private val dataSet = arrayListOf(
		AdapterItem(TOP, false),
		AdapterItem(HEADER, true),
		AdapterItem(BOTTOM, false)
	)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			TOP -> TopViewHolder(
				FragmentTopBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
			HEADER ->
				HeaderViewHolder(
					FragmentHeaderBinding.inflate(
						LayoutInflater.from(parent.context),
						parent,
						false
					)
				)
			else -> BottomViewHolder(
				FragmentBottomBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (dataSet[position].type == TOP) {
			(holder as TopViewHolder).bind()
		} else if (dataSet[position].type == HEADER) {
			(holder as HeaderViewHolder).bind()
		} else if (dataSet[position].type == BOTTOM) {
			(holder as BottomViewHolder).bind()
		}
	}

	override fun getItemCount(): Int = dataSet.size

	override fun getItemViewType(position: Int): Int {
		return dataSet[position].type
	}

	inner class TopViewHolder(val binding: FragmentTopBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			with(binding.banner) {
				adapter = BannerAdapter(activity)
			}
		}
	}

	inner class HeaderViewHolder(val binding: FragmentHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			tabLayout = binding.categoryTab

			if (viewPager != null) {
				TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
					tab.text = "tab$position"
				}.attach()
			}
		}
	}

	inner class BottomViewHolder(val binding: FragmentBottomBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			with(binding.categoryVp) {
				adapter = ContentsVpAdapter(activity as FragmentActivity)
			}

			viewPager = binding.categoryVp

			binding.categoryVp.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
				override fun onPageSelected(position: Int) {
					super.onPageSelected(position)

					pageIdx = position
				}
			})

			if (tabLayout != null) {
				TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
					tab.text = "tab$position"
				}.attach()
			}
		}
	}

	fun isHeader(position: Int) = dataSet[position].type == HEADER

	fun getHeaderView(list: RecyclerView, position: Int): View? {
		val lastIndex =
			if (position < dataSet.size)
				position else dataSet.size - 1
		for (index in lastIndex downTo 0) {
			val model = dataSet[index]
			if (model.type == HEADER) {
				val headerView = LayoutInflater.from(list.context)
						.inflate(R.layout.fragment_header, list, false)

				// 임시로 Tab에 대해 보여드리기 위해 적용한 코드
				// 개선 사항이 필요
				val tabLayout = headerView.findViewById<TabLayout>(R.id.category_tab)
				TabLayoutMediator(tabLayout, viewPager!!) { tab, position ->
					tab.text = "tab$position"
				}.attach()

				return headerView
			}
		}

		return null
	}

	companion object {
		const val TOP = 1
		const val HEADER = 2
		const val BOTTOM = 3
	}
}