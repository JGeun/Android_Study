package jgeun.study.navigationview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jgeun.study.navigationview.databinding.FragmentImportBinding

class ImportFragment : Fragment() {

    private lateinit var binding: FragmentImportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImportBinding.inflate(inflater, container, false)

        return binding.root
    }
}