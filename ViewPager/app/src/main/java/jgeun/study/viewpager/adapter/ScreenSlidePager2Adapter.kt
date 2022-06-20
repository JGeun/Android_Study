package jgeun.study.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import jgeun.study.viewpager.view.ScreenSlidePageFragment

/*
* viewpager2 adapter
*/
class ScreenSlidePager2Adapter(fa: FragmentActivity, private val count: Int) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment(position)
}