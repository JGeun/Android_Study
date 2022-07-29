package jgeun.study.eventbus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jgeun.study.eventbus.databinding.FragmentSecondBinding
import org.greenrobot.eventbus.EventBus

class SecondFragment : Fragment() {

    private lateinit var binding : FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numData = NumData(0)

        binding.button.setOnClickListener {
            numData.count+=1
            EventBus.getDefault().post(numData)
        }
    }
}