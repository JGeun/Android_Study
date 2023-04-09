package jgeun.study.stickyheader

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContentsVpAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

	override fun getItemCount(): Int = 5

	override fun createFragment(position: Int): Fragment
		= ContentsFragment.newInstance()

}