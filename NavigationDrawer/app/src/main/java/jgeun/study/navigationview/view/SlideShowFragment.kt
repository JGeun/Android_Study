package jgeun.study.navigationview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jgeun.study.navigationview.databinding.FragmentHomeBinding
import jgeun.study.navigationview.databinding.FragmentSlideShowBinding

class SlideShowFragment : Fragment(){
    private lateinit var binding: FragmentSlideShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSlideShowBinding.inflate(inflater, container, false)

        return binding.root
    }
}