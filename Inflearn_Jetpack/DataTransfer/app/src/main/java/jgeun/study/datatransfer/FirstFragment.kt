package jgeun.study.datatransfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("requestKey") { resultKey, bundle ->
            val data = bundle.getString("data", "")
            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.button).setOnClickListener {
            setFragmentResult("requestKey", bundleOf("data" to "Hello"))
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}