package jgeun.study.navigationview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jgeun.study.navigationview.databinding.FragmentHomeBinding
import jgeun.study.navigationview.databinding.FragmentToolsBinding

class ToolsFragment : Fragment(){
    private lateinit var binding: FragmentToolsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToolsBinding.inflate(inflater, container, false)

        return binding.root
    }
}