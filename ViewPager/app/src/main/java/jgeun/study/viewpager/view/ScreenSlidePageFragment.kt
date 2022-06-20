package jgeun.study.viewpager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jgeun.study.viewpager.R
import jgeun.study.viewpager.databinding.FragmentScreenSlidePageBinding

//viewpager에 사용할 fragment
class ScreenSlidePageFragment(val position: Int) : Fragment() {

    private lateinit var binding: FragmentScreenSlidePageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenSlidePageBinding.inflate(inflater, container, false)

        // 각 position을 구분하기 위해 색 지정
        when (position) {
            0 -> binding.root.setBackgroundResource(R.color.purple_200)
            1 -> binding.root.setBackgroundResource(R.color.teal_200)
            2 -> binding.root.setBackgroundResource(R.color.purple_700)
            3 -> binding.root.setBackgroundResource(R.color.green)
            4 -> binding.root.setBackgroundResource(R.color.blue)
            else -> binding.root.setBackgroundResource(R.color.red)
        }

        return binding.root
    }
}