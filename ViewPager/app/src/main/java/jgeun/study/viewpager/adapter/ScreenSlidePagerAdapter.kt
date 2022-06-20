package jgeun.study.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import jgeun.study.viewpager.view.ScreenSlidePageFragment

/*
* ViewPager Adapter
*/
class ScreenSlidePagerAdapter(fm: FragmentManager, private val count: Int) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = count

    override fun getItem(position: Int): Fragment = ScreenSlidePageFragment(position)

    override fun getPageTitle(position: Int): CharSequence {
        return "TAB ${(position + 1)}"
    }
}